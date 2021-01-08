package com.copious.training.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Response<T> {

    @JsonProperty(value = "isSuccessful")
    private Boolean isSuccessful;

    private String httpStatus;

    private List<T> payload;

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<T> getPayload() {
        return payload;
    }

    public void setPayload(List<T> payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Response{" +
                "isSuccessful=" + isSuccessful +
                ", httpStatus='" + httpStatus + '\'' +
                ", payload=" + payload +
                '}';
    }
}
