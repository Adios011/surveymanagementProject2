package com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question;

import java.util.List;

public class MultipleChoiceQuestionRest extends QuestionRest{

    private List<OptionRest> options;

    public List<OptionRest> getOptions() {
        return options;
    }

    public void setOptions(List<OptionRest> options) {
        this.options = options;
    }
}
