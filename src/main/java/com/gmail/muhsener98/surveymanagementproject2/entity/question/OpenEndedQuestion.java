package com.gmail.muhsener98.surveymanagementproject2.entity.question;

import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.OpenEndedAnswer;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "open_ended_questions")
public class OpenEndedQuestion extends Question {


    @Override
    public Answer answer(AnswerForm answerForm) {
        assert answerForm.getOpenEndedAnswerText() != null ;

        return new OpenEndedAnswer(this, answerForm.getOpenEndedAnswerText());


    }
}
