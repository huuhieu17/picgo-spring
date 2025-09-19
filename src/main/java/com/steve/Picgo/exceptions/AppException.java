package com.steve.Picgo.exceptions;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {


    private HttpStatus code;
    private String message;

    public AppException(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code.value();
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
