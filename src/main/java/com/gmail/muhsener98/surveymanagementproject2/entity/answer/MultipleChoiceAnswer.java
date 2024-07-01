package com.gmail.muhsener98.surveymanagementproject2.entity.answer;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.MultipleChoiceQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Option;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.*;

@Entity
//@Table(name = "multiple_choice_answers")
@DiscriminatorValue("multiple_choice")
public class MultipleChoiceAnswer extends Answer{

    @ManyToOne
    @JoinColumn(name = "options_id")
    private Option option;

    public MultipleChoiceAnswer(){

    }

    public MultipleChoiceAnswer(Question question ,Option option) {
        super(question);
        this.option = option;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    @Override
    public void delete() {
        option.decreaseCounter();
    }

    @Override
    public void update(AnswerForm answerForm) {
        this.option.decreaseCounter();

        MultipleChoiceQuestion question = (MultipleChoiceQuestion) getQuestion();
        Long newOptionId = answerForm.getChosenOptionId();
        Option newOption = question.findOptionById(newOptionId);
        if(newOption == null)
            throw new IllegalArgumentException("Question " + question.getId() + " has no such Option " + newOptionId);
        newOption.increaseCounter();

        this.option = newOption;
    }

    @Override
    public String answerToString() {
        return option.getOptionText();
    }
}
