package com.gmail.muhsener98.surveymanagementproject2.repository;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.MatrixQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatrixQuestionRepository extends QuestionRepository<MatrixQuestion> {

    @Query("SELECT q FROM MatrixQuestion q " +
            "LEFT JOIN q.survey s " +
            "WHERE s.surveyId = :surveyId")
    @EntityGraph(attributePaths = "innerQuestions" ,type = EntityGraph.EntityGraphType.LOAD)
    List<MatrixQuestion> findAllBySurveyId(@Param("surveyId") String surveyId);
}
