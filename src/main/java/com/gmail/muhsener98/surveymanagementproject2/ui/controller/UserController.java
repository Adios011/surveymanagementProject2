package com.gmail.muhsener98.surveymanagementproject2.ui.controller;

import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.managers.ParticipationManager;
import com.gmail.muhsener98.surveymanagementproject2.service.RegistrationService;
import com.gmail.muhsener98.surveymanagementproject2.service.UserService;
import com.gmail.muhsener98.surveymanagementproject2.shared.dto.UserDto;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.user.RegistrationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.error.UserRest;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.operations.OperationNames;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.operations.OperationStatus;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.operations.OperationStatusModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        Map<Long,AnswerForm> answerFormMap = new HashMap<>();
        answerFormList.forEach(answerForm -> {
            answerFormMap.put(answerForm.getQuestionId() , answerForm);
        });

        participationManager.handleParticipation(userId,surveyId,answerFormMap);

        OperationStatusModel responseModel = new OperationStatusModel(OperationNames.SURVEY_PARTICIPATING , OperationStatus.SUCCESS);
        return ResponseEntity.ok(responseModel);


    }
}
