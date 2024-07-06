package com.gmail.muhsener98.surveymanagementproject2.entity.question;

import com.gmail.muhsener98.surveymanagementproject2.analysis.questions.MultipleChoiceQuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.MultipleChoiceAnswer;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "multiple_choice_questions")
public class MultipleChoiceQuestion extends InnerQuestion {


    @OneToMany(mappedBy = "question", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE} )

    private List<Option> options;





    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
        options.forEach(option -> option.setQuestion(this));
    }


    @Override
    public MultipleChoiceAnswer answer(AnswerForm answerForm) {
        Long chosenOptionId = answerForm.getChosenOptionId();
        if(chosenOptionId == null || chosenOptionId == 0)
            throw new IllegalArgumentException("null or 0 optionId for question " + id);

        Option option = findOptionById(chosenOptionId);
        if(option == null )
            throw new IllegalArgumentException("Question " + this.id + " has no such option " + chosenOptionId);

        option.increaseCounter();

        MultipleChoiceAnswer answer = new  MultipleChoiceAnswer(this , option);

        return answer;
    }

    public MultipleChoiceAnswer answer(Number optionId){
        Option option = findOptionById(optionId.longValue());
        if(option == null)
            throw new IllegalArgumentException("Question " + this.id + " has no such option " + optionId );

        option.increaseCounter();;
        MultipleChoiceAnswer answer = new MultipleChoiceAnswer(this , option);
        return answer;
    }

    @Override
    public MultipleChoiceQuestionAnalysis analyze() {
        MultipleChoiceQuestionAnalysis analysis = new MultipleChoiceQuestionAnalysis(id, this.questionText);
        Map<String,Double> optionTextPercentageMap = new HashMap<>();
        double total = findNumberOfParticipants();

        for (Option option : options) {
            double percentage = calculateOptionPercentage(option.getCounter() , total );
            optionTextPercentageMap.put(option.getOptionText() , percentage);
        }

        analysis.setOptionTextPercentageMap(optionTextPercentageMap);
        return analysis;
    }


    private double calculateOptionPercentage(int x  , double total){
        return (x / total) * 100 ;
    }

    private int findNumberOfParticipants(){
        int sum = 0 ;
        for (Option option : options) {
            sum += option.getCounter()  ;
        }
        return sum;
    }


    public Option findOptionById(Long optionId) {
        if(optionId == null)
            return null ;

        for (Option option : options) {
            if (option.getId().equals(optionId))
                return option;
        }

        return null;


    }






}