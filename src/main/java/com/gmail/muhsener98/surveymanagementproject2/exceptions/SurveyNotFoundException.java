package com.gmail.muhsener98.surveymanagementproject2.exceptions;

import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;

public class SurveyNotFoundException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Survey not found with provided property: ";

    public SurveyNotFoundException(String property){
        super(DEFAULT_MESSAGE + property);
    }
}
