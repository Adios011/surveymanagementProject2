package com.gmail.muhsener98.surveymanagementproject2.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "user already exists with provided field: ";

    public UserAlreadyExistsException(String fieldName){
        super(DEFAULT_MESSAGE + fieldName);
    }
}
