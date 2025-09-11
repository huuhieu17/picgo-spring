package com.steve.Picgo.dtos.user.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateInfoRequest {
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 15)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 15)
    private String lastName;

    private String dateOfBirth; // yyyy-MM-dd
    private Integer gender; // 0,1,2...
}
