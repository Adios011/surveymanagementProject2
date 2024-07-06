package com.gmail.muhsener98.surveymanagementproject2.entity.answer;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public abstract class InnerQuestionAnswer extends Answer {

    @ManyToOne
    @JoinColumn(name = "matrix_answers_id")
    public MatrixAnswer matrixAnswer;


    public MatrixAnswer getMatrixAnswer() {
        return matrixAnswer;
    }

    public void setMatrixAnswer(MatrixAnswer matrixAnswer) {
        this.matrixAnswer = matrixAnswer;
    }


    public InnerQuestionAnswer() {
    }

    public InnerQuestionAnswer(Question question) {
        super(question);
    }

    public abstract void update(Long newAnswer);

}
