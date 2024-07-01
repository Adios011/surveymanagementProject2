package com.gmail.muhsener98.surveymanagementproject2.repository;

import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface SurveyRepository  extends JpaRepository<Survey,Long> {


    Survey findWithoutAssociationsBySurveyId(String surveyId);

    @EntityGraph(attributePaths = "questions" , type = EntityGraph.EntityGraphType.LOAD)
    Survey findWithAllAssociationsBySurveyId(String surveyId);

    @Query("SELECT s FROM Survey s " +
            "WHERE ('all' = :openStatus) " +
            "OR ((s.closeDate > CURRENT_DATE AND 'open' = :openStatus) OR (s.closeDate < CURRENT_DATE AND 'closed' = :openStatus)) ")
    List<Survey> findAllByOpenStatus(String openStatus, Pageable pageable);

    @Query("SELECT s FROM Survey s " +
            "WHERE ('all' = :openStatus) " +
            "OR ((s.closeDate > CURRENT_DATE AND 'open' = :openStatus) OR (s.closeDate < CURRENT_DATE AND 'closed' = :openStatus)) ")
    List<Survey> findAllByOpenStatus(String openStatus);


}
