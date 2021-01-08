package com.copious.training.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GenericResponse<T> implements Serializable {

    @JsonProperty(value = "isSuccessful")
    private final Boolean isSuccessful;

    private final String httpStatus;

    private transient T payload;

    public GenericResponse(Boolean isSuccessful, String httpStatus) {
        this.isSuccessful = isSuccessful;
        this.httpStatus = httpStatus;
    }

    public GenericResponse(Boolean isSuccessful, String httpStatus, T payload) {
        this.isSuccessful = isSuccessful;
        this.httpStatus = httpStatus;
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

    @Override
    public String toString() {
        return "GenericResponse{" +
                "isSuccessful=" + isSuccessful +
                ", httpStatus='" + httpStatus + '\'' +
                ", payload=" + payload +
                '}';
    }
}
