package com.copious.training.exceptions;

import java.io.Serializable;

public class GenericResponse<T> implements Serializable {

    private final Boolean isSuccessful;

    private final String httpStatus;

    private transient T payload;

    public GenericResponse(Boolean isSuccessful, String httpStaus) {
        this.isSuccessful = isSuccessful;
        this.httpStatus = httpStaus;
    }

    public GenericResponse(Boolean isSuccessful, String httpStaus, T payload) {
        this.isSuccessful = isSuccessful;
        this.httpStatus = httpStaus;
        this.payload = payload;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
