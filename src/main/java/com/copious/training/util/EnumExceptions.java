package com.copious.training.util;

import org.springframework.http.HttpStatus;

public enum EnumExceptions {

    EMPLOYEE_NOT_FOUND_EXCEPTIONS("Employee not found", HttpStatus.NOT_FOUND),
    INCORRECT_CREDENTIALS("Credentials incorrect", HttpStatus.FORBIDDEN),
    USER_NOT_FOUND("User with this username does not exists", HttpStatus.NOT_FOUND),
    DB_CONFIG_EXCEPTION("Failed to load DB configurations.", HttpStatus.NOT_FOUND),
    JWT_REQUEST_EXCEPTION("Failed to pass jwt filter.", HttpStatus.BAD_REQUEST),
    MALFORMED_TOKEN("JWT token malformed.", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN("Invalid token", HttpStatus.FORBIDDEN);
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
