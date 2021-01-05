package com.copious.training.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest request) {

        Response response = new Response(ex.getErrorMessage(), ex.getErrorCode().name());

        GenericResponse<Response> genericResponse = new GenericResponse<>(false, ex.getErrorCode().name(), response);

        return handleExceptionInternal(ex, genericResponse, new HttpHeaders(), ex.getErrorCode(), request);
    }

    @ExceptionHandler(ParsingConfigException.class)
    public ResponseEntity<Object> handleXmlParsingException(ParsingConfigException ex, WebRequest request) {

        Response response = new Response();
        response.setMessage(ex.getErrorMessage());

        GenericResponse<Response> genericResponse = new GenericResponse<>(false, ex.getErrorCode().name(), response);

        return handleExceptionInternal(ex, genericResponse, new HttpHeaders(), ex.getErrorCode(), request);
    }

    @ExceptionHandler(CredentialException.class)
    public ResponseEntity<Object> handleCredentialException(CredentialException ex, WebRequest request) {

        Response response = new Response();
        response.setMessage(ex.getErrorMessage());
        response.setStatus(ex.getErrorCode().name());

        GenericResponse<Response> genericResponse = new GenericResponse<>(false, ex.getErrorCode().name(), response);

        return handleExceptionInternal(ex, genericResponse, new HttpHeaders(), ex.getErrorCode(), request);

    }

    @ExceptionHandler(DbConfigException.class)
    public ResponseEntity<Object> handleDbResException(DbConfigException ex, WebRequest request) {

        Response response = new Response();
        response.setMessage(ex.getErrorMessage());
        response.setStatus(ex.getErrorCode().name());

        GenericResponse<Response> genericResponse = new GenericResponse<>(false, ex.getErrorCode().name(), response);

        return handleExceptionInternal(ex, genericResponse, new HttpHeaders(), ex.getErrorCode(), request);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {

        Response response = new Response();
        response.setMessage(ex.getMessage());

        GenericResponse<Response> genericResponse = new GenericResponse<>(false,
                HttpStatus.INTERNAL_SERVER_ERROR.name(), response);

        return handleExceptionInternal(ex, genericResponse, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
