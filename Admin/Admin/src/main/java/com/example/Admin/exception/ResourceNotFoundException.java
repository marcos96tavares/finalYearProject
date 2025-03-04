package com.example.Admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {



    private String resourceName;
    private String fileName;
    private Long fieldValue;

    public ResourceNotFoundException(String resourceName, String fileName, Long fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fileName, fieldValue));
        this.resourceName = resourceName;
        this.fileName = fileName;
        this.fieldValue = fieldValue;
    }
}
