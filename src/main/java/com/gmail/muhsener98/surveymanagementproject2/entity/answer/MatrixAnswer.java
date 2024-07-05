package com.gmail.muhsener98.surveymanagementproject2.entity.answer;

import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@DiscriminatorValue("matrix_answer")
public class MatrixAnswer extends Answer{

    @OneToMany(mappedBy = "matrixAnswer" , cascade = {CascadeType.ALL})
    private List<MultipleChoiceAnswer> multipleChoiceAnswers;



    public List<MultipleChoiceAnswer> getMultipleChoiceAnswers() {
        return multipleChoiceAnswers;
    }

    public void setMultipleChoiceAnswers(List<MultipleChoiceAnswer> multipleChoiceAnswers) {
        this.multipleChoiceAnswers = multipleChoiceAnswers;
        for (MultipleChoiceAnswer multipleChoiceAnswer : multipleChoiceAnswers) {
            multipleChoiceAnswer.setMatrixAnswer(this);
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public void update(AnswerForm answerForm) {

    }

    @Override
    public String getAnswerText() {
        return null;
    }
}
