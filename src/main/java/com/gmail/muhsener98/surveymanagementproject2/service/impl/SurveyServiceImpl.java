package com.gmail.muhsener98.surveymanagementproject2.service.impl;

import com.gmail.muhsener98.surveymanagementproject2.analysis.SurveyAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.SurveyNotFoundException;
import com.gmail.muhsener98.surveymanagementproject2.mapper.SurveyMapper;
import com.gmail.muhsener98.surveymanagementproject2.repository.SurveyRepository;
import com.gmail.muhsener98.surveymanagementproject2.repository.specifications.SurveySpecs;
import com.gmail.muhsener98.surveymanagementproject2.service.QuestionService;
import com.gmail.muhsener98.surveymanagementproject2.service.SurveyService;
import com.gmail.muhsener98.surveymanagementproject2.shared.constants.SurveyOpenStatus;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.survey.SurveyCreationForm;
import org.hibernate.TransientObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionService questionService;


    /**
     * It creates survey.
     * @param surveyCreationForm - details of survey to be created.
     * @return - survey created.
     */
    @Override
    @Transactional
    public Survey createSurvey(SurveyCreationForm surveyCreationForm) {

        Survey surveyToBeSaved = prepareSurveyToSave(surveyCreationForm);

        return surveyRepository.save(surveyToBeSaved);

    }

    private Survey prepareSurveyToSave(SurveyCreationForm surveyCreationForm){
        Survey survey = SurveyMapper.INSTANCE.toEntity(surveyCreationForm);
        survey.setSurveyId("a");
        return survey;
    }


    /**
     * It fetches survey with all associations.
     * To avoid n+1 query problem when accessing questions and associations
     * of their subtypes, this method also loads question hierarchy necessary for participating in survey.
     * @param surveyId  It is not database ID.
     * @return  survey with data necessary for participating in it.
     * @throws SurveyNotFoundException  if survey not found in database.
     */
    @Override
    @Transactional(readOnly = true)
    public Survey findSurveyForParticipation(String surveyId) throws SurveyNotFoundException {
        Survey survey = findSurveyWithAllAssociations(surveyId);

        //To avoid n+1 query problem.
        questionService.loadAssociationsOfSubQuestionsForParticipation(surveyId);

        return survey;


    }


    /**
     * It fetches survey with all associations.
     * To avoid n+1 query problem when accessing questions and associations of their
     * subtypes, this method also loads question hierarchy necessary for survey analysis.
     * @param surveyId - It is not database ID.
     * @return - survey with data necessary to analyze it.
     * @throws SurveyNotFoundException - if survey not found in database.
     */
    @Override
    @Transactional(readOnly = true)
    public Survey findSurveyForAnalysis(String surveyId) throws SurveyNotFoundException{
        Survey survey = findSurveyWithAllAssociations(surveyId);

        //To avoid n+1 query problem.
        questionService.loadAssociationsOfSubQuestionsForAnalysis(surveyId);

        return survey;
    }


    /**
     * It fetches survey without its associations such as questions.
     * @param surveyId - It is not Database ID.
     * @return - survey without associations.
     * @throws SurveyNotFoundException - if survey not found in database.
     */
    @Transactional(readOnly = true)
    public Survey findSurveyWithoutAssociations(String surveyId) throws SurveyNotFoundException{
        Survey survey = surveyRepository.findWithoutAssociationsBySurveyId(surveyId);
        validateSurveyExists(survey, surveyId);

        return survey;
    }


    /**
     * It fetches survey with all associations.
     * However, be careful to call associations of question subtypes.
     * It may results in n+1 query problem.
     * @param surveyId
     * @return - survey with associations
     * @throws SurveyNotFoundException if survey not found in database.
     */
    @Override
    @Transactional(readOnly = true)
    public Survey findSurveyWithAllAssociations(String surveyId) throws SurveyNotFoundException {
        Survey survey = surveyRepository.findWithAllAssociationsBySurveyId(surveyId);
        validateSurveyExists(survey,surveyId);

        return survey;
    }

    private void validateSurveyExists(Survey survey , String surveyId   ){
        if(survey == null)
            throw new SurveyNotFoundException(surveyId);
    }



    @Override
    @Transactional(readOnly = true)
    public Participation participateIn(MyUser myUser , Survey survey, Map<Long, AnswerForm> answerFormMap) {
        if(myUser.getId() == null || survey.getId() == null)
            throw new TransientObjectException("User or survey is transient.");

        if(answerFormMap == null)
            throw new IllegalArgumentException("null answerFormMap");


        Participation participation = survey.participate(answerFormMap);
        participation.setUser(myUser);
        return participation;
    }

    /**
     * It fetches list of survey without associations based on openStatus.
     * @param openStatus - OPEN , CLOSE or ALL
     * @param page -
     * @param limit -
     * @return - List of survey without associations.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Survey> findAllWithoutAssociationsByOpenStatus(SurveyOpenStatus openStatus, int page, int limit) {
        Pageable pageable = PageRequest.of(page,limit);

        //TODO : Re-implement it later in a more polymorphic way.

        if(openStatus.equals(SurveyOpenStatus.OPEN))
            return surveyRepository.findAll(SurveySpecs.isOpen() , pageable).getContent();
        else if(openStatus.equals(SurveyOpenStatus.CLOSED))
            return surveyRepository.findAll(SurveySpecs.isClosed() , pageable).getContent() ;
        else if(openStatus.equals(SurveyOpenStatus.ALL))
            return surveyRepository.findAll(pageable).getContent();
        else
            throw new IllegalArgumentException("Unknown survey open status " + openStatus);


    }












}
