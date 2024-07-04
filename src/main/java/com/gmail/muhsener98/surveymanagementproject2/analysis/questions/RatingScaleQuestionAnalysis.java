package com.gmail.muhsener98.surveymanagementproject2.analysis.questions;


import com.gmail.muhsener98.surveymanagementproject2.entity.answer.RatingScaleAnswer;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.RatingScaleQuestion;
import jakarta.persistence.Column;

public class RatingScaleQuestionAnalysis extends QuestionAnalysis{


    private Integer minRate ;


    private Integer maxRate ;

    private Double averageRate;

    private Integer participantCounter ;

    public RatingScaleQuestionAnalysis(String questionText) {
        super(questionText);
    }





    public Integer getMinRate() {
        return minRate;
    }

    public void setMinRate(Integer minRate) {
        this.minRate = minRate;
    }

    public Integer getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(Integer maxRate) {
        this.maxRate = maxRate;
    }

    public Double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(Double averageRate) {
        this.averageRate = averageRate;
    }

    public Integer getParticipantCounter() {
        return participantCounter;
    }

    public void setParticipantCounter(Integer participantCounter) {
        this.participantCounter = participantCounter;
    }
}
