package com.Progra1.Proyecto.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ResourceNotFoundException.class })
    protected ResponseEntity<Object> ResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), ex.getHttpStatus(), request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ResourceFoundException.class })
    protected ResponseEntity<Object> ResourceFoundException(
            ResourceFoundException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), ex.getHttpStatus(), request);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ResourceIsBeingUsedException.class })
    protected ResponseEntity<Object> ResourceIsBeingUsedException(
            ResourceIsBeingUsedException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), ex.getHttpStatus(), request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UnexpectedErrorException.class })
    protected ResponseEntity<Object> UnexpectedErrorException(
            UnexpectedErrorException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), ex.getHttpStatus(), request);
    }

}