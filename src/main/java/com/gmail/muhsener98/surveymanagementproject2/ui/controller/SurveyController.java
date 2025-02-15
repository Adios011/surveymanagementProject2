package com.gmail.muhsener98.surveymanagementproject2.ui.controller;

import com.gmail.muhsener98.surveymanagementproject2.analysis.SurveyAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.managers.AnalysisManager;
import com.gmail.muhsener98.surveymanagementproject2.mapper.SurveyMapper;
import com.gmail.muhsener98.surveymanagementproject2.service.SurveyService;
import com.gmail.muhsener98.surveymanagementproject2.shared.constants.SurveyOpenStatus;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.survey.SurveyCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.survey.SurveyRest;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.survey.SurveyRestWithoutAssociations;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private AnalysisManager analysisManager;



    //TODO : Only Admin can create a survey
    @PostMapping
    public ResponseEntity<SurveyRestWithoutAssociations> createSurvey(@RequestBody SurveyCreationForm surveyCreationForm) {

        Survey createdSurvey = surveyService.createSurvey(surveyCreationForm);

        SurveyRestWithoutAssociations responseBody = new SurveyRestWithoutAssociations();
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
    @Parameters({
            @Parameter(name = "Authorization" , description = "${swagger.authorizationHeader.description}" , in = ParameterIn.HEADER)
    })
    public ResponseEntity<List<SurveyRestWithoutAssociations>> getAllSurveyByOpenStatus(@RequestParam(name = "openStatus", defaultValue = "OPEN") String openStatus,

                                                                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                                                                        @RequestParam(name = "limit", defaultValue = "10") int limit) {
        SurveyOpenStatus status = SurveyOpenStatus.OPEN;
        openStatus = openStatus.toUpperCase();
        if(openStatus.equals("OPEN"))
            status = SurveyOpenStatus.OPEN;
        else if(openStatus.equals("CLOSED"))
            status = SurveyOpenStatus.CLOSED;
        else if(openStatus.equals("ALL"))
            status = SurveyOpenStatus.ALL;
        else
            throw new IllegalArgumentException("invalid openStatus ");

        List<Survey> surveys = surveyService.findAllWithoutAssociationsByOpenStatus(status , page , limit);

        List<SurveyRestWithoutAssociations> responseBody = SurveyMapper.INSTANCE.toRestWithoutAssociations(surveys);
        return ResponseEntity.ok(responseBody);
    }


    @GetMapping("/{surveyId}/analysis")
    public ResponseEntity<SurveyAnalysis> analyzeSurvey(@PathVariable(name = "surveyId") String surveyId){

        SurveyAnalysis responseBody = analysisManager.analyzeSurvey(surveyId);

        return ResponseEntity.ok(responseBody);
    }


}
