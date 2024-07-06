package com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question;

import java.util.List;

public class MultipleChoiceQuestionCreationForm extends InnerQuestionCreationForm{

    private List<OptionCreationForm> options ;

    public List<OptionCreationForm> getOptions() {
        return options;
    }

    public void setOptions(List<OptionCreationForm> options) {
        this.options = options;
    }
}
