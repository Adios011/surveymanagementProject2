package com.gmail.muhsener98.surveymanagementproject2.service.impl;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.repository.ParticipationRepository;
import com.gmail.muhsener98.surveymanagementproject2.service.ParticipationService;
import com.gmail.muhsener98.surveymanagementproject2.service.QuestionService;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;

@Service("participationService")
public class ParticipationServiceImpl implements ParticipationService {

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private QuestionService questionService;

    @Override
    public boolean checkParticipationExists(MyUser user, Survey survey) {
        if(user.getId() == null || survey.getId() == null)
            throw new RuntimeException("No identifier on user or survey");

        return participationRepository.existsByUserAndSurvey(user ,survey);
    }

    @Override
    @Transactional
    public Participation add(Participation participation) {
        return participationRepository.save(participation);
    }

    @Transactional(readOnly = true)
    public Participation findByUserAndSurvey(MyUser user, Survey survey) {
        Participation participation = participationRepository.findByUserAndSurvey(user , survey);

        return participation;
    }

    public void editParticipation(Participation participation, Map<Long, AnswerForm> answerFormMap){
        participation.edit(answerFormMap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Survey> findAllSurveysParticipatedBy(MyUser user, int page, int limit) {
        Pageable pageable = PageRequest.of(page,limit);

        return participationRepository.findAllSurveysByUser(user, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Survey> findAllSurveysParticipatedBy(MyUser user) {
        return participationRepository.findAllSurveysByUser(user);
    }

    @Override
    @Transactional
    public Participation findParticipationWithAnswers(MyUser user, Survey survey) {
//        System.out.println("****loadAssocationsOFQuestions******");
//        questionService.loadAssociationsOfSubQuestionsForParticipation(survey.getSurveyId());
//        System.out.println("****loadAssocationsOFQuestions******");
        System.out.println("****findWithAnswersByUserAndSurvey******");
        Participation participation = participationRepository.findWithAnswersByUserAndSurvey(user,survey);
        System.out.println("****findWithAnswersByUserAndSurvey******");


        return participation;
    }

    @Override
    @Transactional
    public void delete(Participation participation) {
        if(participation == null )
            return;

        participation.delete();
        participationRepository.delete(participation);
    }


}
