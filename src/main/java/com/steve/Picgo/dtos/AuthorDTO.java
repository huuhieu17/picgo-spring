package com.steve.Picgo.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorDTO {
    Long id;
    String username;
    String fullName;
    String avatarUrl;
}