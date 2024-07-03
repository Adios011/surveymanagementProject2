package com.gmail.muhsener98.surveymanagementproject2.ui.model.request.survey;

import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question.QuestionCreationForm;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyCreationForm implements Serializable {

    private String title;
    private String description;
    private Date closeDate ;
    private List<QuestionCreationForm> questions ;

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

    public List<QuestionCreationForm> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionCreationForm> questions) {
        this.questions = questions;
    }
}
