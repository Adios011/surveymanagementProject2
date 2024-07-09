package com.gmail.muhsener98.surveymanagementproject2.entity.answer;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.MatrixQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.MultipleChoiceQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Option;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

@Entity
//@Table(name = "multiple_choice_answers")
@DiscriminatorValue("multiple_choice")

public class MultipleChoiceAnswer extends InnerQuestionAnswer{

    @ManyToOne(fetch = FetchType.LAZY)
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
        if(option == null)
            return;


        option.decreaseCounter();
        option = null ;
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

    public void update(Long newOptionId){
        if(this.option == null )
            return;

        this.option.decreaseCounter();
        MultipleChoiceQuestion multipleChoiceQuestion = (MultipleChoiceQuestion)  getQuestion();
        Option newOption = multipleChoiceQuestion.findOptionById(newOptionId);
        if(newOption == null)
            throw new IllegalArgumentException("Question " + question.getId() + " has no such Option " + newOptionId);
        newOption.increaseCounter();

        this.option = newOption;
    }

    @Override
    public String getAnswerText() {
        return option.getOptionText();
    }
}
