package com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation;

import java.io.Serializable;
import java.util.Map;

public class AnswerForm implements Serializable {

    private Long questionId;
    private Long chosenOptionId;
    private String openEndedAnswerText ;
    private Integer rate;
    private Map<Long,Long> matrixQuestionAnswerMap;

    public Map<Long, Long> getMatrixQuestionAnswerMap() {
        return matrixQuestionAnswerMap;
    }

    public void setMatrixQuestionAnswerMap(Map<Long, Long> matrixQuestionAnswerMap) {
        this.matrixQuestionAnswerMap = matrixQuestionAnswerMap;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getChosenOptionId() {
        return chosenOptionId;
    }

    public void setChosenOptionId(Long chosenOptionId) {
        this.chosenOptionId = chosenOptionId;
    }

    public String getOpenEndedAnswerText() {
        return openEndedAnswerText;
    }

    public void setOpenEndedAnswerText(String openEndedAnswerText) {
        this.openEndedAnswerText = openEndedAnswerText;
    }
}
