package com.gmail.muhsener98.surveymanagementproject2.service.impl;

import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.MultipleChoiceAnswer;
import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.repository.AnswerRepository;
import com.gmail.muhsener98.surveymanagementproject2.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("answerService")
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository ;


    @Override
    @Transactional(readOnly = true)
    public List<MultipleChoiceAnswer> loadAssociationsOfSubAnswers(Participation participation) {
        List<MultipleChoiceAnswer> answers =   answerRepository.findAllMultipleChoiceAnswersWithQuestionByParticipation(participation);
        answerRepository.findAllMatrixAnswersWithQuestionByParticipation(participation);

        return answers;

    }
}
