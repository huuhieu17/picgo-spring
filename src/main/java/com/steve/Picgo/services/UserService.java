package com.steve.Picgo.services;

import com.steve.Picgo.dtos.user.update.*;
import com.steve.Picgo.entites.UserEntity;
import com.steve.Picgo.exceptions.AppException;
import com.steve.Picgo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;


    @Transactional
    public UserEntity updateInfo(String userId, UpdateInfoRequest request) {
        UserEntity user = getUserOrThrow(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        return userRepository.save(user);
    }

    // Cập nhật username
    @Transactional
    public UserEntity updateUsername(String userId, UpdateUsernameRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(HttpStatus.OK, "Username already exists");
        }
        UserEntity user = getUserOrThrow(userId);
        user.setUsername(request.getUsername());
        return userRepository.save(user);
    }

    // Cập nhật bio
    @Transactional
    public UserEntity updateBio(String userId, UpdateBioRequest request) {
        UserEntity user = getUserOrThrow(userId);
        user.setBio(request.getBio());
        return userRepository.save(user);
    }

    // Cập nhật avatar
    @Transactional
    public UserEntity updateAvatar(String userId, UpdateAvatarRequest request) {
        UserEntity user = getUserOrThrow(userId);
        user.setAvatar(request.getAvatarUrl());
        return userRepository.save(user);
    }

    // Cập nhật cover
    @Transactional
    public UserEntity updateCover(String userId, UpdateCoverRequest request) {
        UserEntity user = getUserOrThrow(userId);
        user.setCover(request.getCoverUrl());
        return userRepository.save(user);
    }

    // helper
    private UserEntity getUserOrThrow(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new AppException(HttpStatus.BAD_GATEWAY, "User not found"));
    }

    public UserEntity getUserByUsernameOrThrow(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new AppException(HttpStatus.BAD_GATEWAY, "User not found"));
    }
}
