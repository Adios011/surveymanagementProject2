package com.gmail.muhsener98.surveymanagementproject2.ui.model.response.answer;

import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question.QuestionRest;

import java.io.Serializable;

public   class AnswerRest  implements Serializable {

    private QuestionRest question;
    private String answerText;


    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public QuestionRest getQuestion() {
        return question;
    }

    public void setQuestion(QuestionRest question) {
        this.question = question;
    }
}
