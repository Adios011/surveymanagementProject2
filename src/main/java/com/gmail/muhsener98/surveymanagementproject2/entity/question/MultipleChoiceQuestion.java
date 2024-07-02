package com.gmail.muhsener98.surveymanagementproject2.entity.question;

import com.gmail.muhsener98.surveymanagementproject2.analysis.MultipleChoiceQuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.analysis.QuestionAnalysis;
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
@NamedQuery(name = "load_multiple_choice_question_with_all_associations_by_survey_id" ,
query = "SELECT mcq FROM MultipleChoiceQuestion mcq " +
        "LEFT JOIN mcq.survey s " +
        "JOIN FETCH mcq.options o " +
        "WHERE s.surveyId = :surveyId")
public class MultipleChoiceQuestion extends Question {


    @OneToMany(mappedBy = "question", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @BatchSize(size = 25)
    private List<Option> options;

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
        options.forEach(option -> option.setQuestion(this));
    }


    @Override
    public Answer answer(AnswerForm answerForm) {
        Long chosenOptionId = answerForm.getChosenOptionId();
        Option option = findOptionById(chosenOptionId);
        if(option == null )
            throw new IllegalArgumentException("Question " + this.id + " has no such option " + chosenOptionId);

        option.increaseCounter();

        MultipleChoiceAnswer answer = new  MultipleChoiceAnswer(this , option);

        return answer;

    }

    @Override
    public MultipleChoiceQuestionAnalysis analyze() {
        MultipleChoiceQuestionAnalysis analysis = new MultipleChoiceQuestionAnalysis(this.questionText);
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