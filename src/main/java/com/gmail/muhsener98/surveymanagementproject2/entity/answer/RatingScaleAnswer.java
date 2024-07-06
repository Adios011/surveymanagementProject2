package com.gmail.muhsener98.surveymanagementproject2.entity.answer;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.RatingScaleQuestion;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.hibernate.Hibernate;

@Entity
@DiscriminatorValue("rating_scale")
public class RatingScaleAnswer extends InnerQuestionAnswer{

    @Column(name = "rate")
    private Integer rate;


    public RatingScaleAnswer(){

    }

    public RatingScaleAnswer(Question question){
        super(question);
    }



    @Override
    public void update(Long newAnswer) {
        RatingScaleQuestion ratingScaleQuestion = (RatingScaleQuestion) getQuestion();

        int newRate = newAnswer.intValue();
        newRate = ratingScaleQuestion.checkAndFixInputRate(newRate);

        ratingScaleQuestion.calculateAveragePointAfterUpdatingAnswer(rate , newRate);
        rate = newRate;
    }

    public RatingScaleAnswer(Question question , int rate ){
        super(question);
        this.rate = rate;
    }

    @Override
    public void delete() {
//         Hibernate.unproxy(getQuestion());
        RatingScaleQuestion ratingScaleQuestion = (RatingScaleQuestion) getQuestion();
        ratingScaleQuestion.calculateAveragePointAfterDeletingAnswer(rate);
        rate = null;
    }

    @Override
    public void update(AnswerForm answerForm) {

        RatingScaleQuestion ratingScaleQuestion = (RatingScaleQuestion) getQuestion();
        System.out.println(answerForm.getRate());
        int newRate = ratingScaleQuestion.checkAndFixInputRate(answerForm.getRate());
        System.out.println(newRate);

        ratingScaleQuestion.calculateAveragePointAfterUpdatingAnswer(rate , newRate);

        rate = newRate;
    }




    @Override
    public String getAnswerText() {
        return String.valueOf(rate);
    }


    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
