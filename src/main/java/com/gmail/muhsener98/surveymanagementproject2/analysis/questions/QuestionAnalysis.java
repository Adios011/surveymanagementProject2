package com.gmail.muhsener98.surveymanagementproject2.analysis.questions;

import java.io.Serializable;

public abstract class QuestionAnalysis implements Serializable {

    private Long questionId ;
    private String questionText;

    public QuestionAnalysis(Long questionId , String questionText){
        this.questionId = questionId;
        this.questionText = questionText;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
