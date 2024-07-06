package com.gmail.muhsener98.surveymanagementproject2.entity.question;

import com.gmail.muhsener98.surveymanagementproject2.analysis.questions.OpenEndedQuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.OpenEndedAnswer;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "open_ended_questions")
@NamedQuery(name = "load_open_ended_questions_with_all_associations_by_survey_id" ,
query = "SELECT oeq FROM OpenEndedQuestion oeq " +
        "LEFT JOIN oeq.survey s " +
        "LEFT JOIN FETCH oeq.answers " +
        "WHERE s.surveyId = :surveyId")
public class OpenEndedQuestion extends Question {

    @OneToMany(mappedBy = "question" , fetch = FetchType.LAZY)
    private List<OpenEndedAnswer> answers;


    public List<OpenEndedAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<OpenEndedAnswer> answers) {
        this.answers = answers;
    }

    @Override
    public Answer answer(AnswerForm answerForm) {
        assert answerForm.getOpenEndedAnswerText() != null ;

        return new OpenEndedAnswer(this, answerForm.getOpenEndedAnswerText());


    }

    @Override
    public OpenEndedQuestionAnalysis analyze() {
        OpenEndedQuestionAnalysis analysis = new OpenEndedQuestionAnalysis(id , this.questionText);
        analysis.setAnswers(convertAnswersToStringList());
        return analysis;
    }


    private List<String> convertAnswersToStringList(){
        List<String> list = new ArrayList<>();
        for (OpenEndedAnswer answer : answers) {
            list.add(answer.getAnswerText());
        }

        return list;
    }
}
