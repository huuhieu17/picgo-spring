package com.steve.Picgo.dtos.auth;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 15, message = "First name must be between 2 and 15 characters")
    @Pattern(
            regexp = "^[A-Za-zÀ-ỹ\\s]+$",
            message = "First name can only contain letters"
    )
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 15, message = "Last name must be between 2 and 15 characters")
    @Pattern(
            regexp = "^[A-Za-zÀ-ỹ\\s]+$",
            message = "First name can only contain letters"
    )
    private String lastName;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(
            regexp = "^[a-zA-Z0-9._]+$",
            message = "Username can only contain letters, numbers, dots, and underscores"
    )
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address format")
    String email;

    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&]).{8,64}$",
            message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character"
    )
    @NotBlank(message = "Password is required")
    String password;
}
