package com.gmail.muhsener98.surveymanagementproject2.ui.model.response.survey;

import java.io.Serializable;
import java.util.*;

public class SurveyRestWithoutDetails implements Serializable {

    private String surveyId ;
    private String title;
    private String description;
    private Date closeDate;
    private int numberOfParticipants;

    public String getSurveyId() {
        return surveyId;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
}
