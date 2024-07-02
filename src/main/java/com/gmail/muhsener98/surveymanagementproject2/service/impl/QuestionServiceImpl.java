package com.gmail.muhsener98.surveymanagementproject2.service.impl;

import com.gmail.muhsener98.surveymanagementproject2.repository.QuestionRepository;
import com.gmail.muhsener98.surveymanagementproject2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;


    @Transactional(readOnly = true)
    public void loadAssociationsOfSubQuestionsForParticipation(String surveyId){
        questionRepository.findAllMultipleChoiceQuestionsBySurveyId(surveyId);
    }

    @Transactional(readOnly = true)
    public void loadAssociationsOfSubQuestionsForAnalysis(String surveyId){
        questionRepository.findAllOpenEndedQuestionsBySurveyId(surveyId);
        questionRepository.findAllMultipleChoiceQuestionsBySurveyId(surveyId);
    }


}
