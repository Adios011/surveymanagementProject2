package com.gmail.muhsener98.surveymanagementproject2.exceptions;

public class UserNotFoundException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "User not found with provided property: ";

    public UserNotFoundException(String message){
        super(DEFAULT_MESSAGE + message);
    }
}
