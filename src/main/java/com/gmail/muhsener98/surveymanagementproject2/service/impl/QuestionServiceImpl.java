package com.gmail.muhsener98.surveymanagementproject2.service.impl;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.MultipleChoiceQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.repository.QuestionRepository;
import com.gmail.muhsener98.surveymanagementproject2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public void loadAssociationsOfSubQuestions(String surveyId){
        questionRepository.findMultipleChoiceQuestionsBySurveyId(surveyId);
    }


}
