package com.gmail.muhsener98.surveymanagementproject2.entity.survey;

import com.gmail.muhsener98.surveymanagementproject2.analysis.SurveyAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.analysis.questions.QuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.survey.SurveyRestWithoutAssociations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SurveyTest {


    @Mock
    List<Question> questions;

    @InjectMocks
    Survey survey;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testParticipate_returnParticipation() {
        Question mockQuestion1 = mock(Question.class);
        Question mockQuestion2 = mock(Question.class);

        when(mockQuestion1.getId()).thenReturn(1L);
        when(mockQuestion2.getId()).thenReturn(2L);

        List<Question> mockQuestions = Arrays.asList(mockQuestion1, mockQuestion2);
        when(questions.iterator()).thenReturn(mockQuestions.iterator());

        Map<Long, AnswerForm> answerFormMap = new HashMap<>();
        AnswerForm answerForm1 = new AnswerForm();
        AnswerForm answerForm2 = new AnswerForm();
        answerFormMap.put(1L, answerForm1);
        answerFormMap.put(2L, answerForm2);

        Answer mockAnswer1 = mock(Answer.class);
        Answer mockAnswer2 = mock(Answer.class);
        when(mockQuestion1.answer(answerForm1)).thenReturn(mockAnswer1);
        when(mockQuestion2.answer(answerForm2)).thenReturn(mockAnswer2);


        Participation participation = survey.participate(answerFormMap);

        verify(mockQuestion1, times(1)).answer(answerForm1);
        verify(mockQuestion2, times(1)).answer(answerForm2);
        assertEquals(2, participation.getAnswers().size());
        assertEquals(survey, participation.getSurvey());

    }


    /**
     * answerFormMap does not contain any answer to one question of survey.
     * That is why, it  returns participation with answer list of size 1.
     */
    @Test
    public void testParticipate_answerMapDoesNotContainAnyAnswerToOneQuestionOfSurvey() {
        Question mockQuestion1 = mock(Question.class);
        Question mockQuestion2 = mock(Question.class);

        when(mockQuestion1.getId()).thenReturn(1L);
        when(mockQuestion2.getId()).thenReturn(2L);

        List<Question> mockQuestions = Arrays.asList(mockQuestion1, mockQuestion2);
        when(questions.iterator()).thenReturn(mockQuestions.iterator());

        // It contains answer for only question with id 1.
        Map<Long, AnswerForm> answerFormMap = new HashMap<>();
        AnswerForm answerForm1 = new AnswerForm();
        AnswerForm answerForm3 = new AnswerForm();
        answerFormMap.put(1L, answerForm1);
        answerFormMap.put(3L, answerForm3);

        Answer answer1 = mock(Answer.class);
//        Answer answer2 = mock(Answer.class);
        when(mockQuestion1.answer(answerForm1)).thenReturn(answer1);
//        when(mockQuestion2.answer(answerForm3)).thenReturn(answer2);

        Participation participation = survey.participate(answerFormMap);

        verify(mockQuestion1, times(1)).answer(answerForm1);
        verify(mockQuestion2, never()).answer(answerForm3);
        assertEquals(participation.getSurvey(), survey);
        assertEquals(participation.getAnswers().size(), 1);

    }


    @Test
    public void testIsOpen_returnTrue() {
        Date futureDate = Date.from(Instant.now().plusMillis(100));

        Survey surveySpy = spy(survey);
        doReturn(futureDate).when(surveySpy).getCloseDate();

        boolean isOpen = surveySpy.isOpen();

        assertTrue(isOpen);

    }

    @Test
    public void testIsOpen_returnsFalse(){
        Date pastDate = Date.from(Instant.now().minusMillis(1));

        Survey surveySpy = spy(survey);
        doReturn(pastDate).when(surveySpy).getCloseDate();

        boolean isOpen = surveySpy.isOpen();

        assertFalse(isOpen);

    }


    @Test
    public void testAnalyze_returnsSurveyAnalysis(){
        Question mockQuestion1 = mock(Question.class);
        Question mockQuestion2 = mock(Question.class);
        List<Question> mockQuestions = Arrays.asList(mockQuestion1,mockQuestion2);
        when(questions.iterator()).thenReturn(mockQuestions.iterator());

        QuestionAnalysis questionAnalysis1 = mock(QuestionAnalysis.class);
        QuestionAnalysis questionAnalysis2 = mock(QuestionAnalysis.class);

        when(mockQuestion1.analyze()).thenReturn(questionAnalysis1 );
        when(mockQuestion2.analyze()).thenReturn(questionAnalysis2);

        SurveyRestWithoutAssociations surveyRestWithoutAssociations = new SurveyRestWithoutAssociations();
        surveyRestWithoutAssociations.setTitle("title");

        Survey surveySpy = spy(survey);
        doReturn(surveyRestWithoutAssociations).when(surveySpy).getDetailsWithoutAssociations();

        SurveyAnalysis surveyAnalysis = surveySpy.analyze();

        assertNotNull(surveyAnalysis);
        assertEquals(surveyAnalysis.getQuestions().size() , 2);
        assertEquals(surveyAnalysis.getSurvey() , surveyRestWithoutAssociations);
    }


    @Test
    public void testAnalyzeQuestions_returnsListOfQuestionAnalysis(){
        Question mockQuestion1 = mock(Question.class);
        Question mockQuestion2 = mock(Question.class);
        List<Question> mockQuestions = Arrays.asList(mockQuestion1,mockQuestion2);
        when(questions.iterator()).thenReturn(mockQuestions.iterator());

        QuestionAnalysis questionAnalysis1 = mock(QuestionAnalysis.class);
        QuestionAnalysis questionAnalysis2 = mock(QuestionAnalysis.class);
        when(mockQuestion1.analyze()).thenReturn(questionAnalysis1 );
        when(mockQuestion2.analyze()).thenReturn(questionAnalysis2);

        List<QuestionAnalysis> result = survey.analyzeQuestions();

        assertNotNull(result);
        assertEquals(result.size() , 2);


    }


    @Test
    public void testGetDetailsWithoutAssociations_returnsSurveyRestWithoutDetails(){
        survey.setTitle("title");

        SurveyRestWithoutAssociations result = survey.getDetailsWithoutAssociations();

        assertEquals(result.getTitle() , survey.getTitle());

    }



}


