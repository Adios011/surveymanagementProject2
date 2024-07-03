package com.gmail.muhsener98.surveymanagementproject2.managers;

import com.gmail.muhsener98.surveymanagementproject2.analysis.questions.QuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.analysis.SurveyAnalysis;

public interface AnalysisManager {


    QuestionAnalysis analyzeQuestion(Long id);
    SurveyAnalysis analyzeSurvey(String surveyId);

}
