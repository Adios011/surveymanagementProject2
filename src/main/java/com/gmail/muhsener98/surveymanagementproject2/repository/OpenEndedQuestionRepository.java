package com.gmail.muhsener98.surveymanagementproject2.repository;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.OpenEndedQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface OpenEndedQuestionRepository extends QuestionRepository<OpenEndedQuestion> {

    @Query("Select q FROM OpenEndedQuestion q " +
            "LEFT JOIN q.survey s " +
            "WHERE s.surveyId = :surveyId")
    @EntityGraph(attributePaths = "answers" , type = EntityGraph.EntityGraphType.LOAD)
    List<OpenEndedQuestion> findAllBySurveyId(@Param("surveyId") String surveyId);



    @EntityGraph(attributePaths = "answers" , type = EntityGraph.EntityGraphType.LOAD)
    List<OpenEndedQuestion> findAllBySurvey( Survey survey);




}
