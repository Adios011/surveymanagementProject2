package com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question;

public class RatingScaleQuestionCreationForm extends InnerQuestionCreationForm{

    private int minRate ;
    private int maxRate;

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
