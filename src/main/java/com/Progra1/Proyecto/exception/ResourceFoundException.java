package com.Progra1.Proyecto.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResourceFoundException extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public ResourceFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
