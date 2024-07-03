package com.gmail.muhsener98.surveymanagementproject2.entity.question;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "options")
@Builder

@AllArgsConstructor
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "option_text" , nullable = false)
    private String optionText ;

    @Column(name = "counter" , nullable = false , columnDefinition = "int default 0")
    private int counter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questions_id")
    private MultipleChoiceQuestion question;


    public Option(){

    }

    public Option(String optionText){
        this.optionText = optionText;
    }


    public void increaseCounter(){
        counter++;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public MultipleChoiceQuestion getQuestion() {
        return question;
    }

    public void setQuestion(MultipleChoiceQuestion question) {
        this.question = question;
    }

    public void decreaseCounter() {
        counter--;
    }
}
