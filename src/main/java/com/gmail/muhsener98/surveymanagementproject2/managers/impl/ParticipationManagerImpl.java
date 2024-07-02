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

    @Override
    public List<Survey> findAllSurveysParticipatedBy(String userId, int page, int limit) {
        MyUser user = userService.findUserWithoutDetails(userId);

        return participationService.findAllSurveysParticipatedBy(user , page, limit);
    }

    @Override
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

//        System.out.println("******findSurveyForParticipation*****");
//        surveyService.findSurveyForParticipation(surveyId);
//        System.out.println("******findSurveyForParticipation*****");

        Participation participation = findParticipationForUserAnswers(user,survey);

//        SurveySpecificParticipationRest detailsToBeReturned = new SurveySpecificParticipationRest();
//        SurveyRestWithoutDetails surveyRestWithoutDetails = new SurveyRestWithoutDetails();
//        BeanUtils.copyProperties(survey , surveyRestWithoutDetails);
//        detailsToBeReturned.setSurvey(surveyRestWithoutDetails);
//
//        List<Answer> answers = participation.getAnswers();
//        List<QuestionRest> questionRests = new ArrayList<>();
//        Map<Long,String> questionIdAnswerTextMap = new HashMap<>();
//        for (Answer answer : answers) {
//            Question question = answer.getQuestion();
//            questionRests.add(QuestionMapper.INSTANCE.toRest(question));
//            questionIdAnswerTextMap.put(question.getId() , answer.getAnswerText());
//        }
//
//        detailsToBeReturned.setQuestions(questionRests);
//        detailsToBeReturned.setQuestionIdAnswerTextMap(questionIdAnswerTextMap);

        return participation;

    }


    private Participation findParticipationForUserAnswers(MyUser user , Survey survey){
        //To avoid n+1 query when accessing options of questions.
        questionService.loadAssociationsOfSubQuestionsForParticipation(survey.getSurveyId());
        return participationService.findParticipationWithAnswers(user,survey);
    }

    @Override
    @Transactional
    public void withdrawFromParticipation(String userId, String surveyId ) {
        MyUser user = userService.findUserWithoutDetails(userId);
        Survey survey = surveyService.findSurveyWithoutAssociations(surveyId);
        validateParticipationCanBeWithdrawn(survey);
        validateHasParticipatedBefore(user, survey);

        System.out.println("*******************************************");
        System.out.println("*******************************************");
        System.out.println("*******************************************");
        Participation participation =findParticipationToWithdraw(user,survey);
        System.out.println("*******************************************");
        System.out.println("*******************************************");
        System.out.println("*******************************************");

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
    private void handleNewParticipation(MyUser user, String surveyId, Map<Long, AnswerForm> answerFormMap) {
        Survey survey = surveyService.findSurveyForParticipation(surveyId);
        Participation participation = surveyService.participateIn(user, survey, answerFormMap);

        participationService.add(participation);


    }


    @Transactional
    private void handleExistingParticipation(MyUser user, Survey survey, Map<Long, AnswerForm> answerFormMap) {
        System.out.println("*****findByUserAndSurvey()********");
        Participation participation = participationService.findByUserAndSurvey(user, survey);
        System.out.println("*****findByUserAndSurvey()********");


        System.out.println("*****editParticipation******");
        participationService.editParticipation(participation, answerFormMap);
        System.out.println("*****editParticipation******");


        participationService.add(participation);
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
