package com.gmail.muhsener98.surveymanagementproject2.service.impl;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.RatingScaleQuestion;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.QuestionNotFoundException;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.messages.QuestionExceptionMessages;
import com.gmail.muhsener98.surveymanagementproject2.repository.MultipleChoiceQuestionRepository;
import com.gmail.muhsener98.surveymanagementproject2.repository.OpenEndedQuestionRepository;
import com.gmail.muhsener98.surveymanagementproject2.repository.QuestionRepository;
import com.gmail.muhsener98.surveymanagementproject2.repository.RatingScaleQuestionRepository;
import com.gmail.muhsener98.surveymanagementproject2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

    @Autowired
    private OpenEndedQuestionRepository openEndedQuestionRepository;
    @Autowired
    private RatingScaleQuestionRepository ratingScaleQuestionRepository;


    @Transactional(readOnly = true)
    public void loadAssociationsOfSubQuestionsForParticipation(String surveyId){
        multipleChoiceQuestionRepository.findAllBySurveyId(surveyId);
    }

    @Transactional(readOnly = true)
    public void loadAssociationsOfSubQuestionsForAnalysis(String surveyId){
        openEndedQuestionRepository.findAllBySurveyId(surveyId);
        multipleChoiceQuestionRepository.findAllBySurveyId(surveyId);
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
