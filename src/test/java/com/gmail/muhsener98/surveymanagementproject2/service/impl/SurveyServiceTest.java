package com.gmail.muhsener98.surveymanagementproject2.service.impl;

import com.gmail.muhsener98.surveymanagementproject2.analysis.SurveyAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.MultipleChoiceAnswer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.OpenEndedAnswer;
import com.gmail.muhsener98.surveymanagementproject2.entity.authorization.Role;
import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.MultipleChoiceQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.OpenEndedQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Option;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.SurveyNotFoundException;
import com.gmail.muhsener98.surveymanagementproject2.mapper.SurveyMapper;
import com.gmail.muhsener98.surveymanagementproject2.repository.SurveyRepository;
import com.gmail.muhsener98.surveymanagementproject2.repository.specifications.SurveySpecs;
import com.gmail.muhsener98.surveymanagementproject2.service.QuestionService;
import com.gmail.muhsener98.surveymanagementproject2.shared.constants.SurveyOpenStatus;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question.MultipleChoiceQuestionCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question.OpenEndedQuestionCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question.OptionCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.survey.SurveyCreationForm;
import jakarta.servlet.http.Part;
import org.hibernate.TransientObjectException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceTest {

    @Mock
    private SurveyRepository surveyRepository;


    @Mock
    Survey survey;


    @Mock
    private QuestionService questionService;

    @InjectMocks
    private SurveyServiceImpl surveyService;


    Survey mockSurvey;
    MyUser mockUser = new MyUser();
    private MultipleChoiceQuestion mockMCQuestion = new MultipleChoiceQuestion();
    private OpenEndedQuestion mockOEQuestion = new OpenEndedQuestion();
    private Participation mockParticipation = new Participation();
    private MultipleChoiceAnswer mockMCAnswer = new MultipleChoiceAnswer();
    private OpenEndedAnswer mockOpenEndedAnswer = new OpenEndedAnswer();

    private Long idSurvey = 1L;
    private String surveyTitle = "title";
    private String surveyId = "alkjsdjkas";
    private String surveyDescription = "description";
    private String question1Text = "question1";
    private Long question1Id = 1L;
    private String question2Text = "question2";
    private Long question2Id = 2L;
    private String option1Text = "option1";
    private Long option1Id = 1L;
    private String option2Text = "option2";
    private Long option2Id = 2L;
    private String question2AnswerText = "open-ended answer";


    private final long idUser = 1L;
    private final String userId = "userId";
    private final String firstName = "Muhammet";
    private final String lastName = "tester";
    private final String encryptedPassword = "password";
    private final String email = "test@gmail.com";
    private final Date birthDate = Date.from(Instant.EPOCH);
    private final List<Role> roles = new ArrayList<>(List.of(new Role("USER")));


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockSurvey = new Survey();
        mockSurvey.setTitle(surveyTitle);
        mockSurvey.setDescription(surveyDescription);
        mockSurvey.setSurveyId(surveyId);
        mockSurvey.setId(idSurvey);

        MultipleChoiceQuestion question1 = new MultipleChoiceQuestion();
        question1.setQuestionText(question1Text);
        question1.setId(question1Id);
        Option option1 = new Option(option1Text);
        option1.setId(option1Id);
        Option option2 = new Option(option2Text);
        option2.setId(option2Id);
        question1.setOptions(List.of(option1, option2));

        OpenEndedQuestion question2 = new OpenEndedQuestion();
        question2.setQuestionText(question2Text);

        mockSurvey.setQuestions(List.of(question1, question2));

    }

    @BeforeEach
    public void setUpMockUser() {
        mockUser = MyUser.builder()
                .setId(idUser)
                .setUserId(userId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEncryptedPassword(encryptedPassword)
                .setEmail(email)
                .setBirthDate(birthDate)
                .setRoles(roles)
                .build();
    }


    @Test
    public void testCreateSurvey_returnsSavedSurvey() {
        when(surveyRepository.save(any(Survey.class))).thenReturn(mockSurvey);
        SurveyCreationForm surveyCreationForm = SurveyCreationForm.builder()
                .title(surveyTitle)
                .description(surveyDescription)
                .closeDate(new Date())
                .build();

        MultipleChoiceQuestionCreationForm mcqForm = new MultipleChoiceQuestionCreationForm();
        mcqForm.setQuestionText(question1Text);
        OptionCreationForm optionCreationForm = new OptionCreationForm();
        optionCreationForm.setOptionText(option1Text);
        OptionCreationForm optionCreationForm1 = new OptionCreationForm();
        optionCreationForm1.setOptionText(option2Text);
        mcqForm.setOptions(List.of(optionCreationForm1, optionCreationForm));

        OpenEndedQuestionCreationForm oeqForm = new OpenEndedQuestionCreationForm();
        oeqForm.setQuestionText(question2Text);

        surveyCreationForm.setQuestions(List.of(mcqForm, oeqForm));


        Survey createdSurvey = surveyService.createSurvey(surveyCreationForm);


        List<Question> createdSurveyQuestions = createdSurvey.getQuestions();
        Question createdQuestion = createdSurveyQuestions.get(0);

        assertNotNull(createdSurvey);
        assertEquals(createdSurvey.getTitle(), surveyCreationForm.getTitle());
        assertEquals(createdQuestion.getQuestionText(), surveyCreationForm.getQuestions().get(0).getQuestionText());
        verify(surveyRepository, times(1)).save(any(Survey.class));


    }


    @Test
    public void testFindSurveyForParticipation_returnsSurvey() {
        when(surveyRepository.findWithAllAssociationsBySurveyId(anyString())).thenReturn(mockSurvey);

        Survey result = surveyService.findSurveyForParticipation(surveyId);

        assertNotNull(result);
        assertEquals(result.getSurveyId(), surveyId);
        verify(surveyRepository, times(1)).findWithAllAssociationsBySurveyId(anyString());
        verify(questionService, times(1)).loadAssociationsOfSubQuestionsForParticipation(anyString());
    }


    @Test
    public void testFindSurveyForParticipation_SurveyNotFound() {
        when(surveyRepository.findWithAllAssociationsBySurveyId(anyString())).thenReturn(null);

        assertThrows(SurveyNotFoundException.class,
                () -> {
                    surveyService.findSurveyForParticipation(surveyId);
                });
    }


    @Test
    public void findSurveyForAnalysis_returnsSurvey() {
        when(surveyRepository.findWithAllAssociationsBySurveyId(anyString())).thenReturn(mockSurvey);

        Survey survey = surveyService.findSurveyForAnalysis(surveyId);

        assertNotNull(survey);
        verify(surveyRepository, times(1)).findWithAllAssociationsBySurveyId(anyString());
        verify(questionService, times(1)).loadAssociationsOfSubQuestionsForAnalysis(anyString());
        ;
    }


    @Test
    public void findSurveyForAnalysis_SurveyNotFound() {
        when(surveyRepository.findWithAllAssociationsBySurveyId(anyString())).thenReturn(null);

        assertThrows(SurveyNotFoundException.class,
                () -> {
                    surveyService.findSurveyForAnalysis(surveyId);
                });

        verify(surveyRepository, times(1)).findWithAllAssociationsBySurveyId(anyString());
        verify(questionService, never()).loadAssociationsOfSubQuestionsForAnalysis(anyString());
        ;

    }


    @Test
    public void findSurveyWithoutAssociations_returnsSurvey() {
        when(surveyRepository.findWithoutAssociationsBySurveyId(anyString())).thenReturn(mockSurvey);

        Survey survey = surveyService.findSurveyWithoutAssociations(surveyId);

        assertNotNull(survey);
        verify(surveyRepository, times(1)).findWithoutAssociationsBySurveyId(anyString());
    }


    @Test
    public void findSurveyWithoutAssociations_SurveyNotFound() {
        when(surveyRepository.findWithoutAssociationsBySurveyId(anyString())).thenReturn(null);

        assertThrows(SurveyNotFoundException.class,
                () -> {
                    surveyService.findSurveyWithoutAssociations(surveyId);
                });

        verify(surveyRepository, times(1)).findWithoutAssociationsBySurveyId(anyString());
    }


    @Test
    public void testParticipateIn_returnsParticipation() {
        Map<Long, AnswerForm> answerFormMap = new HashMap<>();
        AnswerForm form1 = new AnswerForm();
        form1.setChosenOptionId(option1Id);
        form1.setQuestionId(question1Id);
        AnswerForm form2 = new AnswerForm();
        form2.setQuestionId(question2Id);
        form2.setOpenEndedAnswerText(question2AnswerText);
        answerFormMap.put(question1Id, form1);
        answerFormMap.put(question2Id, form2);

        Participation mockParticipation = new Participation();
        mockParticipation.setSurvey(mockSurvey);
        mockParticipation.setUser(mockUser);

        MultipleChoiceAnswer answer = new MultipleChoiceAnswer();
        answer.setParticipation(mockParticipation);
        answer.setOption(new Option(option1Text));
        answer.setQuestion(new MultipleChoiceQuestion());

        when(survey.participate(anyMap())).thenReturn(mockParticipation);


        Participation result = surveyService.participateIn(mockUser, survey, answerFormMap);

        assertNotNull(result);
        assertEquals(result.getSurvey().getSurveyId(), mockSurvey.getSurveyId());
        assertEquals(result.getUser().getFirstName(), mockUser.getFirstName());

        verify(survey, times(1)).participate(anyMap());
    }


    @Test
    public void testParticipateIn_TransientObject() {
        MyUser user = new MyUser();
        Map<Long, AnswerForm> map = new HashMap<>();

        assertThrows(TransientObjectException.class,
                () -> {
                    surveyService.participateIn(user, survey, map);
                });

        verify(survey, never()).participate(anyMap());
    }

    @Test
    public void testParticipateIn2_TransientObject() {
        MyUser user = new MyUser();
        user.setId(10L);
        Survey survey1 = new Survey();


        assertThrows(TransientObjectException.class,
                () -> {
                    surveyService.participateIn(user, survey1, new HashMap<>());
                });

        verify(survey, never()).participate(anyMap());

    }


    @Test
    public void testParticipateIn3_IllegalArgument() {

        assertThrows(IllegalArgumentException.class,
                () -> {
                    surveyService.participateIn(mockUser, survey, null);
                });
    }


    @Test
    public void testFindAllWithoutAssociationsByOpenStatus_returnListOfSurvey() {
        List<Survey> mockSurveyList = List.of(mockSurvey);
        PageImpl<Survey> mockPage = new PageImpl<Survey>(mockSurveyList);

        when(surveyRepository.findAll(any(Specification.class) , any(Pageable.class)))
                .thenReturn(mockPage);


        List<Survey> result = surveyService.findAllWithoutAssociationsByOpenStatus(SurveyOpenStatus.OPEN , 0 , 1);

        assertNotNull(result);
        assertEquals(mockSurveyList.get(0).getSurveyId() , result.get(0) .getSurveyId());

        verify(surveyRepository,times(1)).findAll(any(Specification.class) ,any(Pageable.class));
    }






}
