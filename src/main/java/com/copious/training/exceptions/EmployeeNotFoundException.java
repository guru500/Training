package com.copious.training.exceptions;

import java.io.IOException;

public class EmployeeNotFoundException extends RuntimeException {

    private int errorCode;

    private String errorMessage;

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(String cause, int code) {
        this.errorMessage = cause;
        this.errorCode = code;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
