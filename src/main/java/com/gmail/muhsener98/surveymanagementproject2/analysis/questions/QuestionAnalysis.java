package com.gmail.muhsener98.surveymanagementproject2.analysis.questions;

import java.io.Serializable;

public abstract class QuestionAnalysis implements Serializable {

    private String questionText;

    public QuestionAnalysis(String questionText){
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
