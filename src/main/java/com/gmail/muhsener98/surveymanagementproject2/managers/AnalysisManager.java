package com.gmail.muhsener98.surveymanagementproject2.managers;

import com.gmail.muhsener98.surveymanagementproject2.analysis.QuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.analysis.SurveyAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;

public interface AnalysisManager {


    QuestionAnalysis analyzeQuestion(Long id);
    SurveyAnalysis analyzeSurvey(String surveyId);

}
