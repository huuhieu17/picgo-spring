package com.steve.Picgo.services;

import com.steve.Picgo.dtos.request.PostRequest;
import com.steve.Picgo.dtos.response.PostResponse;
import com.steve.Picgo.entites.MediaItem;
import com.steve.Picgo.entites.Post;
import com.steve.Picgo.entites.UserEntity;
import com.steve.Picgo.exceptions.AppException;
import com.steve.Picgo.mapper.PostMapper;
import com.steve.Picgo.repositories.MediaItemRepository;
import com.steve.Picgo.repositories.PostRepository;
import com.steve.Picgo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;
    MediaItemRepository mediaItemRepository;
    PostMapper postMapper;

    public List<PostResponse> getPostsByUser(String username, int page, int size) {
        UserEntity author = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "User not found"));

        Pageable pageable = PageRequest.of(page, size, org.springframework.data.domain.Sort.by("createdAt").descending());

        Slice<Post> posts = postRepository.findByAuthor(author, pageable);

        return posts.stream()
                .map(postMapper::toPostResponse)
                .toList();
    }

    @Transactional
    public PostResponse createPost(String username, PostRequest postRequest) {
        UserEntity author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 1. Map post (chưa có media)
        Post post = postMapper.toPostEntity(postRequest);
        post.setAuthor(author);
        post.setMedia(null); // clear media để tránh lỗi khi map

        // 2. Save post trước (có id)
        post = postRepository.save(post);

        // 3. Tạo mediaItems từ request (nếu có)
        if (postRequest.getMedia() != null && !postRequest.getMedia().isEmpty()) {
            Post finalPost = post;
            List<MediaItem> mediaItems = postRequest.getMedia().stream()
                    .map(req -> {
                        MediaItem mediaItem = new MediaItem();
                        mediaItem.setType(req.getType());
                        mediaItem.setUrl(req.getUrl());
                        mediaItem.setPost(finalPost); // gắn post_id
                        return mediaItem;
                    })
                    .toList();
            mediaItemRepository.saveAll(mediaItems);
        }
        return postMapper.toPostResponse(post);
    }

    public PostResponse getPostById(UUID id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new AppException(HttpStatus.FORBIDDEN, "Post not found"));
        return mapToResponse(post);
    }

    public void deletePost(UUID id, String username) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.FORBIDDEN, "Post not found"));

        if (!post.getAuthor().getUsername().equals(username)) {
            throw new AppException(HttpStatus.FORBIDDEN,"Unauthorized to delete this post");
        }
        postRepository.delete(post);
    }

    private PostResponse mapToResponse(Post post) {
        return postMapper.toPostResponse(post);
    }
}
