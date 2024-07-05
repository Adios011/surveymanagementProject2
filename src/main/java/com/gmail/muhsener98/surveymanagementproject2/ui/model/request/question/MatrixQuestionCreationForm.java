package com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question;

import java.util.List;

public class MatrixQuestionCreationForm extends QuestionCreationForm{

    private List<MultipleChoiceQuestionCreationForm> multipleChoiceQuestions;

    public List<MultipleChoiceQuestionCreationForm> getMultipleChoiceQuestions() {
        return multipleChoiceQuestions;
    }

    public void setMultipleChoiceQuestions(List<MultipleChoiceQuestionCreationForm> multipleChoiceQuestions) {
        this.multipleChoiceQuestions = multipleChoiceQuestions;
    }
}
