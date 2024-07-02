package com.gmail.muhsener98.surveymanagementproject2.service;

import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.MultipleChoiceAnswer;
import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;

import java.util.List;

public interface AnswerService {

    public List<MultipleChoiceAnswer> loadAssociationsOfSubAnswers(Participation participation);


}
