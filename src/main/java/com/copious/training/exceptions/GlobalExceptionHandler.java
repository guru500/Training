package com.copious.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {

        ErrorMessage message = new ErrorMessage(ex.getErrorCode(), ex.getMessage(), LocalDateTime.now());

        return new ResponseEntity<Object>(message, ex.getErrorCode());
    }

    @ExceptionHandler(ParsingConfigException.class)
    @ResponseBody
    public ResponseEntity<Object> handleXmlParsingException(ParsingConfigException ex) {

        ErrorMessage message = new ErrorMessage(ex.getErrorCode(), ex.getErrorMessage(), LocalDateTime.now());

        return new ResponseEntity<Object>(message, ex.getErrorCode());
    }

    @ExceptionHandler(CredentialExcpetion.class)
    @ResponseBody
    public ResponseEntity<Object> handleCredentialException(CredentialExcpetion ex) {

        ErrorMessage message = new ErrorMessage(ex.getErrorCode(), ex.getErrorMessage(), LocalDateTime.now());

        return new ResponseEntity<Object>(message, ex.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleException(Exception ex) {

        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());

        return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
    }
}
