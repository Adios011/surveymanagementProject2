package com.gmail.muhsener98.surveymanagementproject2.repository;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.MultipleChoiceQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MultipleChoiceQuestionRepository extends QuestionRepository<MultipleChoiceQuestion>{


    @Query("Select q FROM MultipleChoiceQuestion q " +
            "LEFT JOIN q.survey s " +
            "WHERE s.surveyId = :surveyId ")
    @EntityGraph(attributePaths = "options" , type = EntityGraph.EntityGraphType.LOAD)
    List<MultipleChoiceQuestion> findAllBySurveyId(@Param("surveyId") String surveyId);

    @EntityGraph(attributePaths = "options" , type = EntityGraph.EntityGraphType.LOAD)
    List<MultipleChoiceQuestion> findAllBySurvey(Survey survey);
}
