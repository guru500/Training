package com.copious.training.exceptions;

import org.springframework.http.HttpStatus;

public class Response {

    private String message;
    private String status;

    public Response() {
    }

    public Response(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
