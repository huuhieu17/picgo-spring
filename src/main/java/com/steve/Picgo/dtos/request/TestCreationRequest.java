package com.steve.Picgo.dtos.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TestCreationRequest {
    private String id;
    private String name;

    private String email;

    @Size(min = 10, max = 10, message = "Số điện thoại chỉ cho phép 10 ký tự")
    private String phone;

}
