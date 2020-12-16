package com.copious.training.util;

import org.springframework.http.HttpStatus;

public enum EnumExceptions {

    EMPLOYEE_NOT_FOUND_EXCEPTIONS("Employee not found" , HttpStatus.NOT_FOUND),
    INCORRECT_CREDENTIALS("Credentials incorrect", HttpStatus.FORBIDDEN),
    USER_NOT_FOUND("User with this username does not exists" , HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus statusCode;


    EnumExceptions(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
