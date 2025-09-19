package com.steve.Picgo.controllers;

import com.steve.Picgo.dtos.request.PostRequest;
import com.steve.Picgo.dtos.response.ApiResponse;
import com.steve.Picgo.dtos.response.PostResponse;
import com.steve.Picgo.services.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostRequest request,
                                     @AuthenticationPrincipal UserDetails user) {
        ApiResponse apiResponse = new ApiResponse();
        PostResponse post = postService.createPost(user.getUsername(), request);
        apiResponse.setData(post);
        apiResponse.setMessage("Success");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/me/my-posts")
    public List<PostResponse> getUserPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal UserDetails user
    ) {
        return postService.getPostsByUser(user.getUsername(), page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPost(@PathVariable UUID id) {
        ApiResponse apiResponse = new ApiResponse();
        PostResponse post = postService.getPostById(id);
        apiResponse.setData(post);
        apiResponse.setMessage("Success");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable UUID id,
                                        @AuthenticationPrincipal UserDetails user) {
        ApiResponse apiResponse = new ApiResponse();
        postService.deletePost(id, user.getUsername());
        apiResponse.setMessage("Success");
        apiResponse.setData(null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
