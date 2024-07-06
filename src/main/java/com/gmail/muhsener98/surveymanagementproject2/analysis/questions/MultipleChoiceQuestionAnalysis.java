package com.gmail.muhsener98.surveymanagementproject2.analysis.questions;

import java.util.Map;

public class MultipleChoiceQuestionAnalysis extends QuestionAnalysis{


    private Map<String,Double> optionTextPercentageMap ;


    public MultipleChoiceQuestionAnalysis(Long questionId , String questionText) {
        super(questionId , questionText);
    }

    public Map<String, Double> getOptionTextPercentageMap() {
        return optionTextPercentageMap;
    }

    public void setOptionTextPercentageMap(Map<String, Double> optionTextPercentageMap) {
        this.optionTextPercentageMap = optionTextPercentageMap;
    }


}
