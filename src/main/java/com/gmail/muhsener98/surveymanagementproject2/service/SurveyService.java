package com.gmail.muhsener98.surveymanagementproject2.service;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.survey.SurveyCreationForm;

import java.util.Map;

public interface SurveyService {

     Survey createSurvey(SurveyCreationForm surveyCreationForm);

     Survey findSurveyForParticipation(String surveyId);

     Survey findSurveyWithoutAssociations(String surveyId);

     Participation participateIn(MyUser myUser , Survey survey , Map<Long, AnswerForm> answerFormMap);
}
