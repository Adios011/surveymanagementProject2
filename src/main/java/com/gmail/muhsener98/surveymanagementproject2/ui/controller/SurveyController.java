package com.gmail.muhsener98.surveymanagementproject2.ui.controller;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.mapper.QuestionMapper;
import com.gmail.muhsener98.surveymanagementproject2.mapper.SurveyMapper;
import com.gmail.muhsener98.surveymanagementproject2.service.SurveyService;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.survey.SurveyCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.operations.OperationNames;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.operations.OperationStatus;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.operations.OperationStatusModel;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question.QuestionRest;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.survey.SurveyRest;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.survey.SurveyRestWithoutDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    //TODO : Only Admin can create a survey
    @PostMapping
    public ResponseEntity<SurveyRestWithoutDetails> createSurvey(@RequestBody SurveyCreationForm surveyCreationForm) {

        Survey createdSurvey = surveyService.createSurvey(surveyCreationForm);

        SurveyRestWithoutDetails responseBody = new SurveyRestWithoutDetails();
        BeanUtils.copyProperties(createdSurvey, responseBody);

        return ResponseEntity.ok().body(responseBody);
    }


    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyRest> getSurveyWithAllDetails(@PathVariable(name = "surveyId") String surveyId) {
        Survey survey = surveyService.findSurveyForParticipation(surveyId);

        SurveyRest responseBody = SurveyMapper.INSTANCE.toRest(survey);


        return ResponseEntity.ok(responseBody);
    }


    @GetMapping
    public ResponseEntity<List<SurveyRestWithoutDetails>> getAllSurveyByOpenStatus(@RequestParam(name = "openStatus", defaultValue = "open") String openStatus,
                                                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                                                   @RequestParam(name = "limit", defaultValue = "10") int limit) {

        List<Survey> surveys = surveyService.findAllWithoutAssociationsByOpenStatus(openStatus , page , limit);

        List<SurveyRestWithoutDetails> responseBody = new ArrayList<>();
        surveys.forEach(survey -> {
            SurveyRestWithoutDetails surveyRestWithoutDetails = new SurveyRestWithoutDetails();
            BeanUtils.copyProperties( survey ,  surveyRestWithoutDetails);
            responseBody.add(surveyRestWithoutDetails);
        });

        return ResponseEntity.ok(responseBody);


    }
}
