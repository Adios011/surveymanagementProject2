package com.gmail.muhsener98.surveymanagementproject2.managers.impl;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.SurveyCannotBeParticipatedIn;
import com.gmail.muhsener98.surveymanagementproject2.managers.ParticipationManager;
import com.gmail.muhsener98.surveymanagementproject2.service.ParticipationService;
import com.gmail.muhsener98.surveymanagementproject2.service.SurveyService;
import com.gmail.muhsener98.surveymanagementproject2.service.UserService;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service("participationManager")
public class ParticipationManagerImpl  implements ParticipationManager {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserService userService;

    @Autowired
    private ParticipationService participationService;

    @Transactional
    public void handleParticipation(String userId , String surveyId, Map<Long, AnswerForm> answerFormMap){
        Survey survey = surveyService.findSurveyWithoutAssociations(surveyId);
        validateSurveyCanBeParticipatedIn(survey);

        MyUser user = userService.findUserWithoutDetails(userId);

        if(hasParticipatedBefore(user,survey))
            handleExistingParticipation(user,survey,answerFormMap);
        else
            handleNewParticipation(user,surveyId,answerFormMap);

    }




    @Transactional
    private void handleNewParticipation(MyUser user , String surveyId , Map<Long , AnswerForm> answerFormMap){
        Survey survey = surveyService.findSurveyForParticipation(surveyId);
        Participation participation = surveyService.participateIn(user , survey , answerFormMap);

        participationService.add(participation);



    }


    @Transactional
    private void handleExistingParticipation(MyUser user , Survey survey , Map<Long , AnswerForm> answerFormMap){
        System.out.println("*****findByUserAndSurvey()********");
        Participation participation = participationService.findByUserAndSurvey(user,survey);
        System.out.println("*****findByUserAndSurvey()********");


        System.out.println("*****editParticipation******");
        participationService.editParticipation(participation,answerFormMap);
        System.out.println("*****editParticipation******");


        participationService.add(participation);
    }


    @Transactional
    private boolean hasParticipatedBefore(MyUser user , Survey survey){
        return participationService.checkParticipationExists(user , survey);
    }

    @Transactional
    private void validateSurveyCanBeParticipatedIn(Survey survey){
        if(!survey.canBeParticipatedIn())
            throw new SurveyCannotBeParticipatedIn(survey.getSurveyId());
    }
}
