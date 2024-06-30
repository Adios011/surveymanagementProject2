package com.gmail.muhsener98.surveymanagementproject2.service.impl;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.repository.ParticipationRepository;
import com.gmail.muhsener98.surveymanagementproject2.service.ParticipationService;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service("participationService")
public class ParticipationServiceImpl implements ParticipationService {

    @Autowired
    private ParticipationRepository participationRepository;

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


}
