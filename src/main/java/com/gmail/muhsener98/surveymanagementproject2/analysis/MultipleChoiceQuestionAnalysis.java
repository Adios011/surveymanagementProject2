package com.gmail.muhsener98.surveymanagementproject2.analysis;

import java.util.Map;

public class MultipleChoiceQuestionAnalysis extends QuestionAnalysis{


    private Map<String,Double> optionTextPercentageMap ;


    public Map<String, Double> getOptionTextPercentageMap() {
        return optionTextPercentageMap;
    }

    public void setOptionTextPercentageMap(Map<String, Double> optionTextPercentageMap) {
        this.optionTextPercentageMap = optionTextPercentageMap;
    }

    public MultipleChoiceQuestionAnalysis(String questionText) {
        super(questionText);
    }
}
