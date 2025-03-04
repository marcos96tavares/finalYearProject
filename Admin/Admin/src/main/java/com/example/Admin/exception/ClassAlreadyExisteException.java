package com.example.Admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ClassAlreadyExisteException extends RuntimeException{


    private String message;

    public ClassAlreadyExisteException(String message) {
        this.message = message;
    }


}
