package com.gmail.muhsener98.surveymanagementproject2.entity.question;

import com.gmail.muhsener98.surveymanagementproject2.analysis.questions.RatingScaleQuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.RatingScaleAnswer;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "rating_scale_questions")
public class RatingScaleQuestion extends Question {


    @Column(name = "min_rate",nullable = false )
    private int minRate ;

    @Column(name = "max_rate" , nullable = false)
    private int maxRate ;

    @Column(name = "average_rate" , nullable = false , columnDefinition = "double precision default 0.00")
    private double averageRate;

    @Column(name = "participant_counter" , nullable = false , columnDefinition = "int default 0")
    private int participantCounter ;


    @Override
    public RatingScaleAnswer answer(AnswerForm answerForm) {
        int rate = checkAndFixInputRate(answerForm.getRate());
        calculateAveragePointAfterAnsweringQuestion(rate);

        return new RatingScaleAnswer(this, rate );
    }

    private void calculateAveragePointAfterAnsweringQuestion(int rate ){

        rate = checkAndFixInputRate(rate);


        double sum = averageRate * participantCounter;

        sum += rate;
        participantCounter++;

        if(participantCounter == 0 )
            averageRate = 0;
        else
            averageRate = sum / participantCounter;

    }




    public void calculateAveragePointAfterDeletingAnswer(int rate){

        rate = checkAndFixInputRate(rate);


        double sum = averageRate * participantCounter;

        sum -= rate;            //
        participantCounter--;   //

        if(participantCounter == 0)
            averageRate = 0 ;
        else
            averageRate = sum / participantCounter;

    }

    public void calculateAveragePointAfterUpdatingAnswer(int oldRate, int newRate){
        oldRate = checkAndFixInputRate(oldRate);
        newRate = checkAndFixInputRate(newRate);

        double sum = averageRate * participantCounter;
        sum = sum - oldRate + newRate ;

        if(participantCounter == 0 )
            averageRate = 0 ;
        else
            averageRate = sum / participantCounter;

    }


    public int checkAndFixInputRate(int rate){

        System.out.println("min-rate --> " + minRate);
        System.out.println("max--rate --> " + maxRate);

        if(rate < minRate)
            return minRate;
        else if (rate > maxRate)
            return maxRate;
        else
            return rate;
    }

    @Override
    public RatingScaleQuestionAnalysis analyze() {
        RatingScaleQuestionAnalysis analysis = new RatingScaleQuestionAnalysis( id , questionText);
        BeanUtils.copyProperties(this, analysis);
        return analysis;
    }

    public Integer getMinRate() {
        return minRate;
    }

    public void setMinRate(Integer minRate) {
        this.minRate = minRate;
    }

    public Integer getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(Integer maxRate) {
        this.maxRate = maxRate;
    }

    public Double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(Double averageRate) {
        this.averageRate = averageRate;
    }

    public Integer getParticipantCounter() {
        return participantCounter;
    }

    public void setParticipantCounter(Integer participantCounter) {
        this.participantCounter = participantCounter;
    }
}
