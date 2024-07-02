package com.gmail.muhsener98.surveymanagementproject2.ui.controller;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.managers.ParticipationManager;
import com.gmail.muhsener98.surveymanagementproject2.mapper.ParticipationMapper;
import com.gmail.muhsener98.surveymanagementproject2.service.RegistrationService;
import com.gmail.muhsener98.surveymanagementproject2.service.UserService;
import com.gmail.muhsener98.surveymanagementproject2.shared.dto.UserDto;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.user.RegistrationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.participation.ParticipationRest;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.error.UserRest;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.operations.OperationNames;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.operations.OperationStatus;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.operations.OperationStatusModel;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.survey.SurveyRestWithoutAssociations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private ParticipationManager participationManager;


    @PostMapping
    public ResponseEntity<UserRest> createUser(@RequestBody RegistrationForm registrationForm) {
        UserDto createdUserDetails = registrationService.registerUser(registrationForm);

        UserRest responseBody = new UserRest();
        BeanUtils.copyProperties(createdUserDetails, responseBody);

        return ResponseEntity.ok().body(responseBody);
    }


    @PostMapping("/{userId}/surveys/{surveyId}")
    public ResponseEntity<OperationStatusModel> participateInSurvey(@PathVariable(name = "userId") String userId,
                                                                    @PathVariable(name = "surveyId") String surveyId,
                                                                    @RequestBody List<AnswerForm> answerFormList) {

        Map<Long, AnswerForm> answerFormMap = new HashMap<>();
        answerFormList.forEach(answerForm -> {
            answerFormMap.put(answerForm.getQuestionId(), answerForm);
        });

        participationManager.handleParticipation(userId, surveyId, answerFormMap);

        OperationStatusModel responseModel = new OperationStatusModel(OperationNames.SURVEY_PARTICIPATION, OperationStatus.SUCCESS);
        return ResponseEntity.ok(responseModel);


    }


    @GetMapping("/{userId}/surveys")
    public ResponseEntity<List<SurveyRestWithoutAssociations>> getParticipatedSurveysWithoutDetails(@PathVariable(name = "userId") String userId,
                                                                                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                                                                                    @RequestParam(name = "limit", defaultValue = "10") int limit) {

        List<Survey> surveys = participationManager.findAllSurveysParticipatedBy(userId, page, limit);

        List<SurveyRestWithoutAssociations> responseBody = new ArrayList<>();

        surveys.forEach(survey -> {
            SurveyRestWithoutAssociations surveyRestWithoutDetails = new SurveyRestWithoutAssociations();
            BeanUtils.copyProperties(survey, surveyRestWithoutDetails);
            responseBody.add(surveyRestWithoutDetails);
        });

        return ResponseEntity.ok(responseBody);

    }


//    @GetMapping("/{userId}/surveys/{surveyId}")
//    @Transactional
//    public ResponseEntity<SurveySpecificParticipationRest> getUserParticipationForSpecificSurvey(@PathVariable(name = "userId") String userId,
//                                                                                 @PathVariable(name = "surveyId") String surveyId) {
//        SurveySpecificParticipationRest responseBody = participationManager.findUserParticipation(userId , surveyId);
//
////        SurveySpecificParticipationRest surveySpecificParticipationRest = new SurveySpecificParticipationRest();
////
////        System.out.println("****ANKETI MAPLERKEN***");
////        surveySpecificParticipationRest.setSurveyRest(SurveyMapper.INSTANCE.toRest(userParticipation.getSurvey()));
////        System.out.println("****ANKETI MAPLERKEN***");
////
////
////        System.out.println("************CEVAPLARI MAPLERKEN************");
////        List<Answer> answers = userParticipation.getAnswers();
////        Map<Long, String> map = new HashMap<>();
////
////        for (Answer answer : answers) {
////            map.put(answer.getQuestion().getId() , answer.answerToString());
////        }
////
////        surveySpecificParticipationRest.setQuestionIdAnswerTextMap(map);
////        System.out.println("************CEVAPLARI MAPLERKEN************");
//
//
//        return ResponseEntity.ok(responseBody);
//
//    }


    @GetMapping("/{userId}/surveys/{surveyId}")

    public ResponseEntity<ParticipationRest> getParticipatedSurveyWithDetails(@PathVariable(name = "userId") String userId,
                                                                                    @PathVariable(name = "surveyId") String surveyId){
        Participation participation = participationManager.findUserParticipation(userId,surveyId);

        ParticipationRest responseBody = ParticipationMapper.INSTANCE.toRest(participation);

        return ResponseEntity.ok().body(responseBody);
    }


    @DeleteMapping("/{userId}/surveys/{surveyId}")
    public ResponseEntity<OperationStatusModel> withdrawFromParticipation(@PathVariable(name = "userId") String userId,
                                                                          @PathVariable(name = "surveyId") String surveyId){

        participationManager.withdrawFromParticipation(userId , surveyId);

       return ResponseEntity.ok(new OperationStatusModel(OperationNames.SURVEY_WITHDRAW , OperationStatus.SUCCESS));

    }



}
