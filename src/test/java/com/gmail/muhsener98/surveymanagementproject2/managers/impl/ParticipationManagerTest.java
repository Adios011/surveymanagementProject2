package com.gmail.muhsener98.surveymanagementproject2.managers.impl;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.SurveyCannotBeParticipatedIn;
import com.gmail.muhsener98.surveymanagementproject2.service.*;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.servlet.http.Part;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParticipationManagerTest {

    @Mock
    private SurveyService surveyService;

    @Mock
    private UserService userService;

    @Mock
    private ParticipationService participationService;

    @Mock
    private QuestionService questionService;

    @Mock
    private AnswerService answerService;


    @InjectMocks
    private ParticipationManagerImpl participationManager;


    private String userId = "aksjd";
    private String surveyId = "alskdj";


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testHandleParticipation_handlesNewParticipation_SUCCESS(){
        Survey mockSurvey = mock(Survey.class);
        when(surveyService.findSurveyWithoutAssociations(anyString())).thenReturn(mockSurvey);
        when(mockSurvey.canBeParticipatedIn()).thenReturn(true);

        MyUser mockUser = mock(MyUser.class);
        String userId = "userId";
        String surveyId = "surveyId";
        Map<Long,AnswerForm> answerFormMap = new HashMap<>();
        AnswerForm answerForm1 = new AnswerForm();
        AnswerForm answerForm2 = new AnswerForm();
        answerFormMap.put(1L,answerForm1);
        answerFormMap.put(2L,answerForm2);
        when(userService.findUserWithoutDetails(userId)).thenReturn(mockUser);


        when(participationService.checkParticipationExists(mockUser,mockSurvey)).thenReturn(false);


        when(surveyService.findSurveyForParticipation(anyString())).thenReturn(mockSurvey);
        Participation mockParticipation = mock(Participation.class);
        when(surveyService.participateIn(mockUser,mockSurvey,answerFormMap)).thenReturn(mockParticipation);
//        doNothing().when(participationService).add(mockParticipation);


        participationManager.handleParticipation(userId,surveyId,answerFormMap);


        verify(surveyService,times(1)).findSurveyWithoutAssociations(surveyId);
        verify(mockSurvey,times(1)).canBeParticipatedIn();
        verify(userService,times(1)).findUserWithoutDetails(userId);
        verify(participationService,times(1)).checkParticipationExists(mockUser,mockSurvey);
        verify(surveyService,times(1)).findSurveyForParticipation(surveyId);
        verify(surveyService).participateIn(mockUser,mockSurvey,answerFormMap);
        verify(participationService,times(1)).add(mockParticipation );

    }

    @Test
    public void testHandleParticipation_handleExistingParticipation_SUCCESS(){
        String userId = "userId";
        String surveyId ="surveyId";
        Survey mockSurvey = mock(Survey.class);
        when(surveyService.findSurveyWithoutAssociations(surveyId)).thenReturn(mockSurvey);
        when(mockSurvey.canBeParticipatedIn()).thenReturn(true);

        MyUser mockUser = mock(MyUser.class);
        when(userService.findUserWithoutDetails(userId)).thenReturn(mockUser);


        when(participationService.checkParticipationExists(mockUser,mockSurvey)).thenReturn(true);

        Participation mockParticipation = mock(Participation.class);
        when(participationService.findByUserAndSurvey(mockUser,mockSurvey)).thenReturn(mockParticipation);

        Map<Long,AnswerForm> answerFormMap = new HashMap<>();
        participationManager.handleParticipation(userId,surveyId,answerFormMap);

        verify(surveyService,times(1)).findSurveyWithoutAssociations(surveyId);
        verify(mockSurvey,times(1)).canBeParticipatedIn();
        verify(userService,times(1)).findUserWithoutDetails(userId);
        verify(participationService,times(1)).checkParticipationExists(mockUser,mockSurvey);
        verify(participationService,times(1)).findByUserAndSurvey(mockUser,mockSurvey   );
        verify(participationService,times(1)).editParticipation(mockParticipation,answerFormMap);
        verify(participationService,times(1)).add(mockParticipation);

    }


    @Test
    public void testHandleParticipation_SurveyCannotBeParticipatedIn(){
        String userId = "userId";
        String surveyId = "surveyId";

        Survey mockSurvey = mock(Survey.class);

        when(surveyService.findSurveyWithoutAssociations(surveyId)).thenReturn(mockSurvey);
        when(mockSurvey.canBeParticipatedIn()).thenReturn(false);

        assertThrows(SurveyCannotBeParticipatedIn.class ,
                () -> {
            participationManager.handleParticipation(userId,surveyId,new HashMap<>());
                });

        verify(surveyService,times(1)).findSurveyWithoutAssociations(surveyId);
        verify(mockSurvey,times(1)).canBeParticipatedIn();

        verify(userService,never()).findUserWithoutDetails(anyString());

    }



    @Test
    public void testFindAllSurveysParticipatedBy_returnListOFSurvey(){


        Survey mockSurvey1 = mock(Survey.class);
        Survey mockSurvey2 = mock(Survey.class);
        List<Survey> mockSurveys = Arrays.asList(mockSurvey1,mockSurvey2);

        MyUser mockUser = mock(MyUser.class);

        when(userService.findUserWithoutDetails(userId)).thenReturn(mockUser);
        when(participationService.findAllSurveysParticipatedBy(mockUser,0,1)).thenReturn(mockSurveys);

        List<Survey> result = participationManager.findAllSurveysParticipatedBy(userId,0,1);

        assertEquals(2,result.size());
        assertEquals(result.get(0) , mockSurvey1);
        assertEquals(result.get(1) , mockSurvey2);

        verify(userService,times(1)).findUserWithoutDetails(userId);
        verify(participationService,times(1)).findAllSurveysParticipatedBy(mockUser,0,1);

    }



}
