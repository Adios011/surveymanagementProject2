package com.gmail.muhsener98.surveymanagementproject2.managers.impl;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.ParticipationNotFoundException;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.SurveyCannotBeParticipatedIn;
import com.gmail.muhsener98.surveymanagementproject2.managers.ParticipationManager;
import com.gmail.muhsener98.surveymanagementproject2.service.*;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("participationManager")
public class ParticipationManagerImpl implements ParticipationManager {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserService userService;

    @Autowired
    private ParticipationService participationService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Transactional
    public void handleParticipation(String userId, String surveyId, Map<Long, AnswerForm> answerFormMap) {
        Survey survey = surveyService.findSurveyWithoutAssociations(surveyId);
        validateSurveyCanBeParticipatedIn(survey);

        MyUser user = userService.findUserWithoutDetails(userId);

        if (hasParticipatedBefore(user, survey))
            handleExistingParticipation(user, survey, answerFormMap);
        else
            handleNewParticipation(user, surveyId, answerFormMap);

    }

    @Transactional
    private void handleNewParticipation(MyUser user, String surveyId, Map<Long, AnswerForm> answerFormMap) {
        Survey survey = surveyService.findSurveyForParticipation(surveyId);
        Participation participation = surveyService.participateIn(user, survey, answerFormMap);

        participationService.add(participation);
    }

    @Transactional
    private void handleExistingParticipation(MyUser user, Survey survey, Map<Long, AnswerForm> answerFormMap) {

        Participation participation = participationService.findParticipationWithAnswersAndQuestions(user,survey);
        participationService.editParticipation(participation, answerFormMap);

        participationService.add(participation);
    }


    @Transactional(readOnly = true)
    private Participation findParticipationWithAnswersAndQuestions(MyUser user , Survey survey){
        //To avoid n+1 query when accessing options of questions.
        questionService.loadAssociationsOfSubQuestionsForParticipation(survey.getSurveyId());
        return participationService.findParticipationWithAnswers(user,survey);
    }



    @Override
    @Transactional
    public List<Survey> findAllSurveysParticipatedBy(String userId, int page, int limit) {
        MyUser user = userService.findUserWithoutDetails(userId);

        return participationService.findAllSurveysParticipatedBy(user , page, limit);
    }

    @Override
    @Transactional
    public List<Survey> findAllSurveysParticipatedBy(String userId) {
        MyUser user = userService.findUserWithoutDetails(userId);

        return participationService.findAllSurveysParticipatedBy(user );
    }

    @Override
    @Transactional
    public Participation findUserParticipation(String userId, String surveyId) {
        MyUser user = userService.findUserWithoutDetails(userId);
        Survey survey = surveyService.findSurveyWithoutAssociations(surveyId);

        validateHasParticipatedBefore(user,survey);

        return participationService.findParticipationWithAnswersAndQuestions(user,survey);

    }




    @Override
    @Transactional
    public void withdrawFromParticipation(String userId, String surveyId ) {
        MyUser user = userService.findUserWithoutDetails(userId);
        Survey survey = surveyService.findSurveyWithoutAssociations(surveyId);
        validateParticipationCanBeWithdrawn(survey);
        validateHasParticipatedBefore(user, survey);


        Participation participation =findParticipationToWithdraw(user,survey);


        participationService.delete( participation);
    }

    private Participation findParticipationToWithdraw(MyUser user, Survey survey){
        Participation participation = participationService.findParticipationWithAnswers(user,survey);
        answerService.loadAssociationsOfSubAnswers(participation);
        return participation;
    }

    private void validateParticipationCanBeWithdrawn(Survey survey){
        if(!survey.isOpen())
            throw new SurveyCannotBeParticipatedIn("survey is closed that's why answers cannot be edited.");
    }

    private void validateHasParticipatedBefore(MyUser user , Survey survey){
        if(!hasParticipatedBefore(user , survey))
            throw new ParticipationNotFoundException("No such participation of " + user.getUserId() + " for survey " + survey.getSurveyId());
    }









    @Transactional
    private boolean hasParticipatedBefore(MyUser user, Survey survey) {
        return participationService.checkParticipationExists(user, survey);
    }

    @Transactional
    private void validateSurveyCanBeParticipatedIn(Survey survey) {
        if (!survey.canBeParticipatedIn())
            throw new SurveyCannotBeParticipatedIn(survey.getSurveyId());
    }
}
