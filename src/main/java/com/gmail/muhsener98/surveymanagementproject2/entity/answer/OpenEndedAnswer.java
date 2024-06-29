package com.gmail.muhsener98.surveymanagementproject2.entity.answer;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
//@Table(name = "open_ended_answers")
@DiscriminatorValue("open_ended")
public class OpenEndedAnswer extends Answer{

    private String text;


    public OpenEndedAnswer(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
