package com.steve.Picgo.services;

import com.steve.Picgo.config.MultipartInputStreamFileResource;
import com.steve.Picgo.entites.UserFile;
import com.steve.Picgo.repositories.UserFileRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UploadService {

    UserFileRepository userFileRepository;
    RestTemplate restTemplate = new RestTemplate();

    private static final String MEDIA_UPLOAD_URL = "https://media-content-cdn.imsteve.dev/upload";
    private static final String MEDIA_DELETE_URL = "https://media-content-cdn.imsteve.dev/delete/";

    @Transactional
    public String uploadUserFile(MultipartFile file, String userId, UserFile.FileType type) {
        try {
            // Xoá file cũ nếu đã có
            userFileRepository.findByUserIdAndType(userId, type).ifPresent(existing -> {
                deleteUserFile(existing.getFileName());
                userFileRepository.delete(existing);
            });

            // Tạo body multipart
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));
            body.add("userId", userId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(MEDIA_UPLOAD_URL, requestEntity, Map.class);

            String url = (String) response.getBody().get("url");
            String fileName = url.substring(url.lastIndexOf('/') + 1);

            // Lưu mới vào DB
            UserFile userFile = UserFile.builder()
                    .userId(userId)
                    .fileName(fileName)
                    .fileUrl(url)
                    .type(type)
                    .build();
            userFileRepository.save(userFile);

            return url;

        } catch (Exception e) {
            throw new RuntimeException("Upload failed", e);
        }
    }

    @Transactional
    public void deleteUserFileByType(String userId, UserFile.FileType type) {
        userFileRepository.findByUserIdAndType(userId, type).ifPresent(existing -> {
            deleteUserFile(existing.getFileName());
            userFileRepository.delete(existing);
        });
    }

    public void deleteUserFile(String fileName) {
        try {
            restTemplate.delete(MEDIA_DELETE_URL + fileName);
        } catch (Exception e) {
            // log hoặc ignore
        }
    }
}
