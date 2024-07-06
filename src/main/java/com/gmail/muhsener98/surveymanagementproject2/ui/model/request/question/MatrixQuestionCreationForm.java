package com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question;

import java.util.List;

public class MatrixQuestionCreationForm extends QuestionCreationForm{

    private  List<InnerQuestionCreationForm> innerQuestions;

    public List<InnerQuestionCreationForm> getInnerQuestions() {
        return innerQuestions;
    }

    public void setInnerQuestions(List<InnerQuestionCreationForm> innerQuestions) {
        this.innerQuestions = innerQuestions;
        System.out.println("************************** +" + innerQuestions);
    }
}
