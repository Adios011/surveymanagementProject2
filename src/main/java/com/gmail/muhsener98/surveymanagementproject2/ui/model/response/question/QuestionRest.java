package com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question;

import java.io.Serializable;

public abstract class QuestionRest implements Serializable {

    private Long id ;

    private String questionText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
