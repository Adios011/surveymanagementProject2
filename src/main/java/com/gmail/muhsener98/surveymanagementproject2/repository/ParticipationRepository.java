package com.gmail.muhsener98.surveymanagementproject2.repository;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository  extends JpaRepository<Participation , Long> {

    boolean existsByUserAndSurvey(MyUser user , Survey survey);


    Participation findByUserAndSurvey(MyUser user , Survey survey);
}
