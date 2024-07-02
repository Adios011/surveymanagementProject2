package com.gmail.muhsener98.surveymanagementproject2.managers;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;

import java.util.List;
import java.util.Map;

public interface ParticipationManager {

     void handleParticipation(String userId , String surveyId, Map<Long, AnswerForm> answerFormMap);

     List<Survey> findAllSurveysParticipatedBy(String userId, int page, int limit);
     List<Survey> findAllSurveysParticipatedBy(String userId);

     Participation findUserParticipation(String userId, String surveyId);

     void withdrawFromParticipation(String userId , String surveyId);
}
