package com.gmail.muhsener98.surveymanagementproject2.exceptions;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;

public class ParticipationNotFoundException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Participation not found.";

    public ParticipationNotFoundException(String message){
        super(message);
    }

}
