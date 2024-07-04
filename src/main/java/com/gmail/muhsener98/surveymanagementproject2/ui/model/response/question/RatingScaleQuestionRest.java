package com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question;

public class RatingScaleQuestionRest extends QuestionRest{

    private int minRate ;
    private int maxRate ;


    public int getMinRate() {
        return minRate;
    }

    public void setMinRate(int minRate) {
        this.minRate = minRate;
    }

    public int getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(int maxRate) {
        this.maxRate = maxRate;
    }
}
