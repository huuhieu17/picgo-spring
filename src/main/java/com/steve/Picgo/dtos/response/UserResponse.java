package com.steve.Picgo.dtos.response;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;

    String firstName;

    String lastName;

    String username;

    String email;

    String password;

    String dateOfBirth;

    int gender;

    String avatar;

    String cover;

    String bio;

    String role = "ROLE_USER";
}
