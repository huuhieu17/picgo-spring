package com.steve.Picgo.services;

import com.steve.Picgo.dtos.user.update.UserDetailImpl;
import com.steve.Picgo.entites.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDetailServiceImpl implements UserDetailsService {
    UserService userService;

    @Override
    @Transactional
    public UserDetailImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userService.getUserByUsernameOrThrow(username);

        return UserDetailImpl.build(userEntity);
    }
}