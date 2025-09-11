package com.steve.Picgo.services;

import com.steve.Picgo.dtos.auth.AuthenticateResponse;
import com.steve.Picgo.dtos.auth.LoginRequest;
import com.steve.Picgo.dtos.auth.RegisterRequest;
import com.steve.Picgo.entites.UserEntity;
import com.steve.Picgo.exceptions.AppException;
import com.steve.Picgo.mapper.UserMapper;
import com.steve.Picgo.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    UserRepository userRepository;

    UserMapper userMapper;

    JwtService jwtService;

    public AuthenticateResponse athenticate(LoginRequest loginRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        var user = userRepository.findByUsernameOrEmail(loginRequest.getLogin(), loginRequest.getLogin()).orElseThrow(()->
                new AppException(HttpStatus.UNAUTHORIZED, "User not found"));
        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        AuthenticateResponse authenticateResponse = new AuthenticateResponse();
        authenticateResponse.setAuthenticated(matches);
        if (matches) {
            String token = jwtService.generateToken(user.getUsername(), Map.of("role", user.getRole()) );
            authenticateResponse.setToken(token);
        }
        return authenticateResponse;
    }

    public boolean createUser(@Valid RegisterRequest registerRequest){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        Check exits
        if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new AppException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        if(userRepository.existsByUsername(registerRequest.getUsername())){
            throw new AppException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        UserEntity userEntity = userMapper.toUserEntity(registerRequest);
        try {
            userRepository.save(userEntity);
            return true;
        } catch (Exception e) {
            throw  new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving user");
        }

    }
}
