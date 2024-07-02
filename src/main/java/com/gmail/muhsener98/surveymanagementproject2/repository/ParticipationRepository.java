package com.gmail.muhsener98.surveymanagementproject2.repository;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipationRepository  extends JpaRepository<Participation , Long> {

    boolean existsByUserAndSurvey(MyUser user , Survey survey);


    Participation findByUserAndSurvey(MyUser user , Survey survey);

    @EntityGraph(attributePaths = {"answers" , "answers.question"  } , type = EntityGraph.EntityGraphType.LOAD)
    Participation findWithAnswersByUserAndSurvey(MyUser user , Survey survey);



    @Query(name ="find_All_Surveys_Participated_By_User_Without_Associations" )
    List<Survey> findAllSurveysByUser(@Param("user") MyUser user);

    @Query(name ="find_All_Surveys_Participated_By_User_Without_Associations" )
    List<Survey> findAllSurveysByUser(@Param("user") MyUser user, Pageable pageable);

}
