package com.gmail.muhsener98.surveymanagementproject2.service;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public interface ParticipationService {

    boolean checkParticipationExists(MyUser user , Survey survey);
     Participation add(Participation participation);

     Participation findByUserAndSurvey(MyUser user , Survey survey);

     void editParticipation(Participation participation , Map<Long, AnswerForm> answerFormMap);


    List<Survey> findAllSurveysParticipatedBy(MyUser user, int page, int limit);
    List<Survey> findAllSurveysParticipatedBy(MyUser user);

    Participation findParticipationWithAllDetails(MyUser user, Survey survey);
}
