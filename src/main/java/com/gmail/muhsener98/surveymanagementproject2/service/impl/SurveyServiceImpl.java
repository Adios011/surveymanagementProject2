package com.gmail.muhsener98.surveymanagementproject2.service.impl;

import com.gmail.muhsener98.surveymanagementproject2.analysis.SurveyAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.SurveyNotFoundException;
import com.gmail.muhsener98.surveymanagementproject2.mapper.SurveyMapper;
import com.gmail.muhsener98.surveymanagementproject2.repository.SurveyRepository;
import com.gmail.muhsener98.surveymanagementproject2.service.QuestionService;
import com.gmail.muhsener98.surveymanagementproject2.service.SurveyService;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.survey.SurveyCreationForm;
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


    @Override
    public Survey createSurvey(SurveyCreationForm surveyCreationForm) {

        Survey surveyToBeSaved = prepareSurveyToSave(surveyCreationForm);

        return surveyRepository.save(surveyToBeSaved);

    }

    @Override
    @Transactional(readOnly = true)
    public Survey findSurveyForParticipation(String surveyId) {
        Survey survey = surveyRepository.findWithAllAssociationsBySurveyId(surveyId);
        if(survey == null)
            throw new SurveyNotFoundException(surveyId);

        //To avoid n+1 query problem.
        questionService.loadAssociationsOfSubQuestionsForParticipation(surveyId);

        return survey;


    }


    @Override
    @Transactional(readOnly = true)
    public Survey findSurveyForAnalysis(String surveyId){
        Survey survey = surveyRepository.findWithAllAssociationsBySurveyId(surveyId);
        if(survey == null)
            throw new SurveyNotFoundException(surveyId);

        //To avoid n+1 query problem.
        questionService.loadAssociationsOfSubQuestionsForAnalysis(surveyId);

        return survey;
    }



    @Transactional(readOnly = true)
    public Survey findSurveyWithoutAssociations(String surveyId){
        Survey survey = surveyRepository.findWithoutAssociationsBySurveyId(surveyId);
        if(survey == null)
            throw new SurveyNotFoundException(surveyId);

        return survey;
    }

    @Override
    public Participation participateIn(MyUser myUser , Survey survey, Map<Long, AnswerForm> answerFormMap) {
        Participation participation = survey.participate(answerFormMap);
        participation.setUser(myUser);
        return participation;
    }

    @Override
    public List<Survey> findAllWithoutAssociationsByOpenStatus(String openStatus, int page, int limit) {
        Pageable pageable = PageRequest.of(page,limit);

        return surveyRepository.findAllByOpenStatus(openStatus ,pageable);
    }


    @Override
    @Transactional
    public SurveyAnalysis analyzeSurvey(String surveyId) {
        Survey survey = findSurveyForAnalysis(surveyId);
        return survey.analyze();
    }




    private Survey prepareSurveyToSave(SurveyCreationForm surveyCreationForm){
        Survey survey = SurveyMapper.INSTANCE.toEntity(surveyCreationForm);
        survey.setSurveyId("a");
        return survey;
    }




}
