package com.gmail.muhsener98.surveymanagementproject2.managers.impl;

import com.gmail.muhsener98.surveymanagementproject2.analysis.questions.QuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.analysis.SurveyAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.managers.AnalysisManager;
import com.gmail.muhsener98.surveymanagementproject2.service.QuestionService;
import com.gmail.muhsener98.surveymanagementproject2.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("analysisManager")
public class AnalysisManagerImpl implements AnalysisManager {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private SurveyService surveyService;

    @Override
    @Transactional
    public QuestionAnalysis analyzeQuestion(Long id) {
        Question question = findQuestionForAnalysis(id);
        return question.analyze();
    }

    private Question findQuestionForAnalysis(Long id)  {
        return questionService.findQuestion(id);
    }



    @Override
    @Transactional
    public SurveyAnalysis analyzeSurvey(String surveyId) {
        Survey survey = findSurveyForAnalysis(surveyId);
        return survey.analyze();
    }

    private Survey findSurveyForAnalysis(String surveyId){
        questionService.loadAssociationsOfSubQuestionsForAnalysis(surveyId);
        return surveyService.findSurveyWithAllAssociations(surveyId);

    }
}
