package com.gmail.muhsener98.surveymanagementproject2.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class SurveyDto implements Serializable {

    private Long id ;
    private String surveyId ;
    private String title;
    private String description ;
    private Date closeDate ;


    public SurveyDto(Long id, String surveyId, String title, String description, Date closeDate) {
        this.id = id;
        this.surveyId = surveyId;
        this.title = title;
        this.description = description;
        this.closeDate = closeDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
