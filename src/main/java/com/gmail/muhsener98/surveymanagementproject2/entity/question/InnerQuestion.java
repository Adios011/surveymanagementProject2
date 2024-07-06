package com.gmail.muhsener98.surveymanagementproject2.entity.question;

import com.gmail.muhsener98.surveymanagementproject2.analysis.questions.QuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.InnerQuestionAnswer;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public abstract class InnerQuestion extends Question{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matrix_questions_id")
    private MatrixQuestion matrixQuestion;


    public MatrixQuestion getMatrixQuestion() {
        return matrixQuestion;
    }

    protected  void  setMatrixQuestion(MatrixQuestion matrixQuestion){
        this.matrixQuestion = matrixQuestion;
    }

    protected  abstract InnerQuestionAnswer answer(Number answer);


}
