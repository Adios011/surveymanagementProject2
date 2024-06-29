package com.gmail.muhsener98.surveymanagementproject2.entity.question;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;



}
