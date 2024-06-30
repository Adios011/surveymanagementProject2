package com.gmail.muhsener98.surveymanagementproject2.exceptions;

public class SurveyCannotBeParticipatedIn extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Survey cannot be participated in : " ;

    public SurveyCannotBeParticipatedIn(String property){
        super(DEFAULT_MESSAGE + property)    ;
    }
}
