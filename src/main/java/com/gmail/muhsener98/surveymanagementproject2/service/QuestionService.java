package com.gmail.muhsener98.surveymanagementproject2.service;

public interface QuestionService {

     void loadAssociationsOfSubQuestionsForParticipation(String surveyId);
     void loadAssociationsOfSubQuestionsForAnalysis(String surveyId);
}
