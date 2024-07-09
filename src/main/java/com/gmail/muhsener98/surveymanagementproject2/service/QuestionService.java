package com.gmail.muhsener98.surveymanagementproject2.service;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.QuestionNotFoundException;

public interface QuestionService {

     void loadAssociationsOfMultipleChoiceQuestions(String surveyId);
     void loadAssociationsOfMatrixQuestions(String surveyId);
     void loadAssociationsOfSubQuestionsForAnalysis(String surveyId);

     Question findQuestion(Long id ) throws QuestionNotFoundException;
}
