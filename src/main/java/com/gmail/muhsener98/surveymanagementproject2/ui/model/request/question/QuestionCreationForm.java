package com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")

@JsonSubTypes({
        @JsonSubTypes.Type(value = MultipleChoiceQuestionCreationForm.class, name = "multiple-choice-question"),
        @JsonSubTypes.Type(value = OpenEndedQuestionCreationForm.class, name = "open-ended-question")}
)
public abstract class QuestionCreationForm implements Serializable {

    private String questionText;
    private String type;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
