package com.gmail.muhsener98.surveymanagementproject2.entity.answer;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.*;

@Entity
@Table(name = "answers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questions_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participations_id")
    private Participation participation;

    public Answer(Question question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public abstract void delete();
    public abstract void update(AnswerForm answerForm);
    public abstract String getAnswerText();

    public Answer (){

    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Participation getParticipation() {
        return participation;
    }

    public void setParticipation(Participation participation) {
        this.participation = participation;
    }
}
