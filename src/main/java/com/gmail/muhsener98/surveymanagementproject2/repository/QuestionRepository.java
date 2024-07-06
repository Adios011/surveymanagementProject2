package com.gmail.muhsener98.surveymanagementproject2.repository;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Optional;

public interface QuestionRepository<T extends Question> extends JpaRepository<T,Long> {

//    @EntityGraph(value = "question_for_analysis" , type = EntityGraph.EntityGraphType.LOAD)
    @Override
    @EntityGraph(value = "question_for_analysis" , type = EntityGraph.EntityGraphType.LOAD)
    Optional<T> findById(Long id );


//    @Query(name =  "load_multiple_choice_question_with_all_associations_by_survey_id" )
//    List<MultipleChoiceQuestion> findAllMultipleChoiceQuestionsBySurveyId(@Param("surveyId") String surveyId);
//
//    @Query(name = "load_open_ended_questions_with_all_associations_by_survey_id")
//    List<OpenEndedQuestion> findAllOpenEndedQuestionsBySurveyId(@Param("surveyId") String surveyId);










}
