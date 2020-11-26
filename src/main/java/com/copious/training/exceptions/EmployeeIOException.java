package com.copious.training.exceptions;

import java.io.IOException;

public class EmployeeIOException extends IOException {

    public EmployeeIOException(String message) {
        super("Employee input output exception");
    }
}
