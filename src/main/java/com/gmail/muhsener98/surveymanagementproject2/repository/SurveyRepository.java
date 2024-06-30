package com.gmail.muhsener98.surveymanagementproject2.repository;

import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository  extends JpaRepository<Survey,Long> {


    Survey findWithoutAssociationsBySurveyId(String surveyId);

    @EntityGraph(attributePaths = "questions" , type = EntityGraph.EntityGraphType.LOAD)
    Survey findWithAllAssociationsBySurveyId(String surveyId);
}
