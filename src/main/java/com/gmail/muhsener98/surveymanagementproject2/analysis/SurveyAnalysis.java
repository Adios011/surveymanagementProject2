package com.gmail.muhsener98.surveymanagementproject2.analysis;

import com.gmail.muhsener98.surveymanagementproject2.analysis.questions.QuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.survey.SurveyRestWithoutAssociations;

import java.util.List;

public class SurveyAnalysis {

    private SurveyRestWithoutAssociations survey ;
    private List<QuestionAnalysis> questions ;


    public SurveyRestWithoutAssociations getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyRestWithoutAssociations survey) {
        this.survey = survey;
    }

    public List<QuestionAnalysis> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionAnalysis> questions) {
        this.questions = questions;
    }
}
