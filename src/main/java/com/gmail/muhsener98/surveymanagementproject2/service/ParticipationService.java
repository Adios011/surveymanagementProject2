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

    Participation findParticipationWithAnswers(MyUser user, Survey survey);

    /**
     * It fetches Participation with answers associations.
     * This method also loads question hierarchy to avoid n+1 query problem.
     * @param user participant of survey
     * @param survey survey that has been participated in
     * @return participation with answers fetched eagerly.
     */

    @Transactional(readOnly = true)
    Participation findParticipationWithAnswersAndQuestions(MyUser user , Survey survey) ;

    void delete(Participation participation);
}
