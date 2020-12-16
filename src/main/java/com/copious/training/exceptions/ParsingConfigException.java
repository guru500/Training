package com.copious.training.exceptions;

import com.copious.training.util.EnumExceptions;
import org.springframework.http.HttpStatus;

public class ParsingConfigException extends RuntimeException {

    private HttpStatus errorCode;

    private String errorMessage;

    public ParsingConfigException(EnumExceptions ex) {
        this.errorCode = ex.getStatusCode();
        this.errorMessage = ex.getMessage();
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
