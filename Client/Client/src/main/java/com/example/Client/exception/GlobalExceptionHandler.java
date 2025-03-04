package com.example.Client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFound(ResourceNotFoundException exception,
                                                                      WebRequest webRequest) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .path(webRequest.getDescription(false))
                .errorCode("RESOURCE_NOT_FOUND")
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }



    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleEmailAlreadyExists(EmailAlreadyExistsException emailAlreadyExistsException,
                                                                 WebRequest webRequest) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(emailAlreadyExistsException.getMessage())
                .path(webRequest.getDescription(false))
                .errorCode("EMAIL_ALREADY_EXIST")
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

    }

}
