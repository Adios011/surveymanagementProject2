package com.gmail.muhsener98.surveymanagementproject2.service;

import com.gmail.muhsener98.surveymanagementproject2.analysis.SurveyAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.survey.SurveyCreationForm;
import org.apache.catalina.User;

import java.util.List;
import java.util.Map;

public interface SurveyService {

     Survey createSurvey(SurveyCreationForm surveyCreationForm);

     Survey findSurveyForParticipation(String surveyId);

     Survey findSurveyWithoutAssociations(String surveyId);

     Survey findSurveyWithAllAssociations(String surveyId);

     Survey findSurveyForAnalysis(String surveyId);

     Participation participateIn(MyUser myUser , Survey survey , Map<Long, AnswerForm> answerFormMap);

     List<Survey> findAllWithoutAssociationsByOpenStatus(String openStatus , int page , int limit);

     SurveyAnalysis analyzeSurvey(String surveyId);
}
