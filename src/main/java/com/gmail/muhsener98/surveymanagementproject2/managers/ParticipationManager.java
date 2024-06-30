package com.gmail.muhsener98.surveymanagementproject2.managers;

import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;

import java.util.Map;

public interface ParticipationManager {

     void handleParticipation(String userId , String surveyId, Map<Long, AnswerForm> answerFormMap);
}
