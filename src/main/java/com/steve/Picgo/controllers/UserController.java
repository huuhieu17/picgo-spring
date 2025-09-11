package com.steve.Picgo.controllers;

import com.steve.Picgo.dtos.response.ApiResponse;
import com.steve.Picgo.dtos.user.update.UpdateAvatarRequest;
import com.steve.Picgo.dtos.user.update.UpdateCoverRequest;
import com.steve.Picgo.dtos.user.update.UpdateInfoRequest;
import com.steve.Picgo.dtos.user.update.UpdateUsernameRequest;
import com.steve.Picgo.entites.UserEntity;
import com.steve.Picgo.entites.UserFile;
import com.steve.Picgo.services.UploadService;
import com.steve.Picgo.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class UserController {
    UserService userService;
    UploadService uploadService;

    @PutMapping("/me/info")
    public ApiResponse<?> updateName(
            @AuthenticationPrincipal(expression = "id") String userId,
            @Valid @RequestBody UpdateInfoRequest request) {
        userService.updateInfo(userId, request);
        ApiResponse<UserEntity> response = new ApiResponse<>();
        response.setMessage("Success");
        response.setCode(HttpStatus.OK.value());
        return response;
    }

    @PutMapping("/me/username")
    public ApiResponse<?> updateUsername(
            @AuthenticationPrincipal(expression = "id") String userId,
            @Valid @RequestBody UpdateUsernameRequest request) {
        userService.updateUsername(userId, request);
        ApiResponse<UserEntity> response = new ApiResponse<>();
        response.setMessage("Success");
        response.setCode(HttpStatus.OK.value());
        return response;
    }

    @PostMapping("/me/avatar")
    public ApiResponse<?> uploadAvatar(@AuthenticationPrincipal(expression = "id") String userId,
                                          @RequestParam("file") MultipartFile file) {
        String url = uploadService.uploadUserFile(file, userId, UserFile.FileType.AVATAR);
        userService.updateAvatar(userId, new UpdateAvatarRequest(url));
        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Success");
        response.setCode(HttpStatus.OK.value());
        response.setData(url);
        return response;
    }

    @DeleteMapping("/me/avatar")
    public ApiResponse<?> deleteAvatar(@AuthenticationPrincipal(expression = "id") String userId) {
        uploadService.deleteUserFileByType(userId, UserFile.FileType.AVATAR);
        userService.updateAvatar(userId, new UpdateAvatarRequest(null));
        ApiResponse<UserEntity> response = new ApiResponse<>();
        response.setMessage("Avatar Delete Success");
        response.setCode(HttpStatus.OK.value());
        return response;
    }

    @PostMapping("/me/cover")
    public ApiResponse<?> uploadCover(@AuthenticationPrincipal(expression = "id") String userId,
                                         @RequestParam("file") MultipartFile file) {
        String url = uploadService.uploadUserFile(file, userId, UserFile.FileType.COVER);
        userService.updateCover(userId, new UpdateCoverRequest(url));
        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Success");
        response.setCode(HttpStatus.OK.value());
        response.setData(url);
        return response;
    }

    @DeleteMapping("/me/cover")
    public ApiResponse<?> deleteCover(@AuthenticationPrincipal(expression = "id") String userId) {
        uploadService.deleteUserFileByType(userId, UserFile.FileType.COVER);
        userService.updateCover(userId, new UpdateCoverRequest(null));
        ApiResponse<UserEntity> response = new ApiResponse<>();
        response.setMessage("Cover Delete Success");
        response.setCode(HttpStatus.OK.value());
        return response;
    }
}
