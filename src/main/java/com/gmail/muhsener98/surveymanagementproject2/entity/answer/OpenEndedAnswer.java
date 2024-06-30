package com.gmail.muhsener98.surveymanagementproject2.entity.answer;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.operations.OperationStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
//@Table(name = "open_ended_answers")
@DiscriminatorValue("open_ended")
public class OpenEndedAnswer extends Answer{

    private String text;


    public OpenEndedAnswer(){

    }

    public OpenEndedAnswer(Question question , String text) {
        super(question);
        this.text = text;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void delete() {
        text = "";
    }

    @Override
    public void update(AnswerForm answerForm) {
        this.text = answerForm.getOpenEndedAnswerText();
    }
}
