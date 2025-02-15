package com.gmail.muhsener98.surveymanagementproject2.service;

import com.gmail.muhsener98.surveymanagementproject2.analysis.SurveyAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.SurveyNotFoundException;
import com.gmail.muhsener98.surveymanagementproject2.shared.constants.SurveyOpenStatus;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.survey.SurveyCreationForm;


import java.util.List;
import java.util.Map;

public interface SurveyService {

     Survey createSurvey(SurveyCreationForm surveyCreationForm);


     /**
      * It fetches survey with all associations.
      * To avoid n+1 query problem when accessing questions and associations
      * of their subtypes, this method also loads question hierarchy necessary for participating in survey.
      * @param surveyId - It is not database ID.
      * @return - survey with data necessary for participating in it.
      * @throws SurveyNotFoundException - if survey not found in database.
      */
     Survey findSurveyForParticipation(String surveyId);

     Survey findSurveyWithoutAssociations(String surveyId);

     Survey findSurveyWithAllAssociations(String surveyId);

     Survey findSurveyForAnalysis(String surveyId);

     Participation participateIn(MyUser myUser , Survey survey , Map<Long, AnswerForm> answerFormMap);


    List<Survey> findAllWithoutAssociationsByOpenStatus(SurveyOpenStatus openStatus, int page, int limit);


}
