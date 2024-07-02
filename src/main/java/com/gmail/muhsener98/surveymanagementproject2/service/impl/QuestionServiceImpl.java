package com.gmail.muhsener98.surveymanagementproject2.service.impl;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.QuestionNotFoundException;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.messages.QuestionExceptionMessages;
import com.gmail.muhsener98.surveymanagementproject2.repository.QuestionRepository;
import com.gmail.muhsener98.surveymanagementproject2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Override
    @Transactional(readOnly = true)
    public Question findQuestion(Long id) throws QuestionNotFoundException {
        Optional<Question> optional = questionRepository.findById(id);
        if(optional.isEmpty())
            throw new QuestionNotFoundException(QuestionExceptionMessages.NO_QUESTION_BY_ID + id );

        return optional.get();
    }


}
