package com.gmail.muhsener98.surveymanagementproject2.exceptions.handler;

import com.gmail.muhsener98.surveymanagementproject2.exceptions.SurveyNotFoundException;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.UserAlreadyExistsException;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception , WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), new Date());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(SurveyNotFoundException.class)
    private ResponseEntity<ErrorResponse> handleSurveyNotFoundException(SurveyNotFoundException exception, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage() , new Date());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
