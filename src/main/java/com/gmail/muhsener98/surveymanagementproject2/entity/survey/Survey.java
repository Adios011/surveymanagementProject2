package com.gmail.muhsener98.surveymanagementproject2.entity.survey;

import com.gmail.muhsener98.surveymanagementproject2.analysis.questions.QuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.analysis.SurveyAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.survey.SurveyRestWithoutAssociations;
import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "surveys")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "survey_id", nullable = false)
    private String surveyId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "close_date", nullable = false)
    private Date closeDate;

    @Column(name = "nofParticipants", nullable = false, columnDefinition = "int default 0")
    private int numberOfParticipants;

    @OneToMany(mappedBy = "survey", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Question> questions;


    public Survey(){

    }

    public Survey(Long id, String surveyId, String title, String description, Date closeDate, int numberOfParticipants) {
        this.id = id;
        this.surveyId = surveyId;
        this.title = title;
        this.description = description;
        this.closeDate = closeDate;
        this.numberOfParticipants = numberOfParticipants;
    }

    public boolean canBeParticipatedIn() {
        return isOpen();
    }


    public Participation participate(Map<Long, AnswerForm> answerFormMap) {

        if(answerFormMap == null )
            throw new IllegalArgumentException("null answerFormMap.");

        List<Answer> answers = new ArrayList<>();
        for (Question question : questions) {
            Long questionId = question.getId();
            if (!answerFormMap.containsKey(questionId))
                continue;

            AnswerForm form = answerFormMap.get(questionId);
            Answer answer = question.answer(form);
            answers.add(answer);
        }

        return new Participation(this,answers);

    }


    public boolean isOpen() {
        return getCloseDate().after(new Date());
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

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        questions.forEach(question -> question.setSurvey(this));
    }

    public SurveyAnalysis analyze() {
        SurveyAnalysis surveyAnalysis = new SurveyAnalysis();

        surveyAnalysis.setSurvey(getDetailsWithoutAssociations());
        surveyAnalysis.setQuestions(analyzeQuestions());
        return surveyAnalysis;

    }

    public List<QuestionAnalysis> analyzeQuestions(){
        List<QuestionAnalysis> questionAnalyses = new ArrayList<>();
        for (Question question : questions) {
            questionAnalyses.add(question.analyze());
        }
        return questionAnalyses;
    }

    public SurveyRestWithoutAssociations getDetailsWithoutAssociations(){
        SurveyRestWithoutAssociations surveyRestWithoutAssociations = new SurveyRestWithoutAssociations();
        BeanUtils.copyProperties(this , surveyRestWithoutAssociations);
        return surveyRestWithoutAssociations;
    }
}
