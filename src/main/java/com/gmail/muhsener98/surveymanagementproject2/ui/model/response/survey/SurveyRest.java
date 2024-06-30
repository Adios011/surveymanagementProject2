package com.gmail.muhsener98.surveymanagementproject2.ui.model.response.survey;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question.QuestionRest;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SurveyRest implements Serializable {

    private String surveyId ;
    private String title;
    private String description;
    private Date closeDate;
    private int numberOfParticipants;

    private List<QuestionRest> questions;

    public String getSurveyId() {
        return surveyId;
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

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public List<QuestionRest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionRest> questions) {
        System.out.println("Do you call it ? ");
        this.questions = questions;
    }
}
