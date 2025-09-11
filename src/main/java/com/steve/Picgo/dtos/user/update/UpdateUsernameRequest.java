package com.steve.Picgo.dtos.user.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUsernameRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
}
