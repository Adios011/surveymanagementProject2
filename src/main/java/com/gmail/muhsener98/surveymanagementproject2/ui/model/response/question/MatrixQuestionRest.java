package com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question;


import java.util.List;


public class MatrixQuestionRest extends QuestionRest {

    private List<Long> innerQuestionIds;

    public List<Long> getInnerQuestionIds() {
        return innerQuestionIds;
    }

    public void setInnerQuestionIds(List<Long> innerQuestionIds) {
        this.innerQuestionIds = innerQuestionIds;
    }
}
