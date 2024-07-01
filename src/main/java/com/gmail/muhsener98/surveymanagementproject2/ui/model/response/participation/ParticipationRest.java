package com.gmail.muhsener98.surveymanagementproject2.ui.model.response.participation;

import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.answer.AnswerRest;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.survey.SurveyRestWithoutDetails;

import java.io.Serializable;
import java.util.List;

public class ParticipationRest implements Serializable {

    private SurveyRestWithoutDetails survey;
    private List<AnswerRest> answers ;

    public SurveyRestWithoutDetails getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyRestWithoutDetails survey) {
        this.survey = survey;
    }

    public List<AnswerRest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRest> answers) {
        this.answers = answers;
    }
}
