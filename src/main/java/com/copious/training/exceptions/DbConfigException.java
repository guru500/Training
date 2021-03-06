package com.copious.training.exceptions;

import com.copious.training.util.EnumExceptions;
import org.springframework.http.HttpStatus;

public class DbConfigException extends RuntimeException {

    private HttpStatus errorCode;

    private String errorMessage;

    public DbConfigException(EnumExceptions exceptions) {
        this.errorCode = exceptions.getStatusCode();
        this.errorMessage = exceptions.getMessage();
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
