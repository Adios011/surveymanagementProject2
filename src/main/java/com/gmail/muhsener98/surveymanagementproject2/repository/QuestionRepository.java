package com.gmail.muhsener98.surveymanagementproject2.repository;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.MultipleChoiceQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.OpenEndedQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository<T extends Question> extends JpaRepository<T,Long> {




//    @Query(name =  "load_multiple_choice_question_with_all_associations_by_survey_id" )
//    List<MultipleChoiceQuestion> findAllMultipleChoiceQuestionsBySurveyId(@Param("surveyId") String surveyId);
//
//    @Query(name = "load_open_ended_questions_with_all_associations_by_survey_id")
//    List<OpenEndedQuestion> findAllOpenEndedQuestionsBySurveyId(@Param("surveyId") String surveyId);










}
