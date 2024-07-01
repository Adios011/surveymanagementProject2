package com.gmail.muhsener98.surveymanagementproject2.entity.question;

import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.*;


@Entity
@Table(name = "questions")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id ;

    @Column(name = "question_text" , nullable = false)
    protected String questionText ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surveys_id")
    protected Survey survey;


    public abstract Answer answer(AnswerForm answerForm);


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
