package com.gmail.muhsener98.surveymanagementproject2.entity.answer;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import jakarta.persistence.*;

@Entity
@Table(name = "answers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

    @ManyToOne
    @JoinColumn(name = "questions_id")
    private Question question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
