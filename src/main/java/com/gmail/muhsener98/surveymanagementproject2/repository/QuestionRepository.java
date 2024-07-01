package com.gmail.muhsener98.surveymanagementproject2.repository;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.MultipleChoiceQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {


    @Query(name =  "load_multiple_choice_question_with_all_associations_by_survey_id" )
    List<Question> findMultipleChoiceQuestionsBySurveyId(@Param("surveyId") String surveyId);



}
