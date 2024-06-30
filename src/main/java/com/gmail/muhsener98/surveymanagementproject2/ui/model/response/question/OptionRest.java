package com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question;

import java.io.Serializable;

public class OptionRest implements Serializable {

    private Long id;
    private String optionText;

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
