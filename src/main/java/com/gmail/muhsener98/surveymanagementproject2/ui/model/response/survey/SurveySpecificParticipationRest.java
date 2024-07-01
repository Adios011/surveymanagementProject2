package com.gmail.muhsener98.surveymanagementproject2.ui.model.response.survey;

import java.io.Serializable;
import java.util.Map;

public class SurveySpecificParticipationRest implements Serializable {

    private SurveyRest surveyRest;
    private Map<Long,String> questionIdAnswerTextMap;

    public SurveyRest getSurveyRest() {
        return surveyRest;
    }

    public void setSurveyRest(SurveyRest surveyRest) {
        this.surveyRest = surveyRest;
    }

    public Map<Long, String> getQuestionIdAnswerTextMap() {
        return questionIdAnswerTextMap;
    }

    public void setQuestionIdAnswerTextMap(Map<Long, String> questionIdAnswerTextMap) {
        this.questionIdAnswerTextMap = questionIdAnswerTextMap;
    }
}
