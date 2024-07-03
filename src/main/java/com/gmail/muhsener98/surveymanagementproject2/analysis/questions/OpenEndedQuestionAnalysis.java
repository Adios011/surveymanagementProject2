package com.gmail.muhsener98.surveymanagementproject2.analysis.questions;

import java.util.List;

public class OpenEndedQuestionAnalysis extends QuestionAnalysis{

    private List<String> answers ;

    public OpenEndedQuestionAnalysis(String questionText) {
        super(questionText);
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
