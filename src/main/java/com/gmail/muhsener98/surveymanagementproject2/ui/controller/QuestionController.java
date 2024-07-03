package com.gmail.muhsener98.surveymanagementproject2.ui.controller;

import com.gmail.muhsener98.surveymanagementproject2.analysis.questions.QuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.managers.AnalysisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/questions")
public class QuestionController {

    @Autowired
    private AnalysisManager analysisManager;

    @GetMapping("/{id}/analysis")
    public QuestionAnalysis analyzeQuestion(@PathVariable("id") Long id){
        return analysisManager.analyzeQuestion(id);
    }
}

