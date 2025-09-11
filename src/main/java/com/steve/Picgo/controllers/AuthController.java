package com.steve.Picgo.controllers;

import com.steve.Picgo.dtos.auth.AuthenticateResponse;
import com.steve.Picgo.dtos.auth.LoginRequest;
import com.steve.Picgo.dtos.auth.RegisterRequest;
import com.steve.Picgo.dtos.response.ApiResponse;
import com.steve.Picgo.services.AuthService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        ApiResponse response = new ApiResponse();
        AuthenticateResponse authenticateResponse = authService.athenticate(loginRequest);
        response.setData(authenticateResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ApiResponse registerAccount(@Valid @RequestBody RegisterRequest registerRequest) {
        ApiResponse apiResponse = new ApiResponse();
        boolean isRegisterSuccess = authService.createUser(registerRequest);
        apiResponse.setCode(isRegisterSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage(isRegisterSuccess ? "Register Success" : "Register Failed");
        return apiResponse;
    }
}
