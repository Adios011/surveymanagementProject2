package com.gmail.muhsener98.surveymanagementproject2.analysis.questions;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;

import java.util.List;

public class MatrixQuestionAnalysis extends QuestionAnalysis {


    private List<Long> innerQuestionIds ;



    public MatrixQuestionAnalysis(Long questionId , String questionText) {
        super(questionId , questionText);
    }


    public List<Long> getInnerQuestionIds() {
        return innerQuestionIds;
    }

    public void setInnerQuestionIds(List<Long> innerQuestionIds) {
        this.innerQuestionIds = innerQuestionIds;
    }


}
