package com.gmail.muhsener98.surveymanagementproject2.ui.model.response.participation;

import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.answer.AnswerRest;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.survey.SurveyRestWithoutAssociations;

import java.io.Serializable;
import java.util.List;

public class ParticipationRest implements Serializable {

    private SurveyRestWithoutAssociations survey;
    private List<AnswerRest> answers ;

    public SurveyRestWithoutAssociations getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyRestWithoutAssociations survey) {
        this.survey = survey;
    }

    public List<AnswerRest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRest> answers) {
        this.answers = answers;
    }
}
