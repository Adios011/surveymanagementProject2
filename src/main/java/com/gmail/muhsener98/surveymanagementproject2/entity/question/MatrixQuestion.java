package com.gmail.muhsener98.surveymanagementproject2.entity.question;

import com.gmail.muhsener98.surveymanagementproject2.analysis.questions.QuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.MatrixAnswer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.MultipleChoiceAnswer;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "matrix_questions")
public class MatrixQuestion extends Question{

    @OneToMany(mappedBy = "matrixQuestion" , cascade = {CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REMOVE})
    private List<MultipleChoiceQuestion> multipleChoiceQuestions ;

    public List<MultipleChoiceQuestion> getMultipleChoiceQuestions() {
        return multipleChoiceQuestions;
    }

    public void setMultipleChoiceQuestions(List<MultipleChoiceQuestion> multipleChoiceQuestions) {
        this.multipleChoiceQuestions = multipleChoiceQuestions;
        for (MultipleChoiceQuestion multipleChoiceQuestion : multipleChoiceQuestions) {
            multipleChoiceQuestion.setMatrixQuestion(this);

        }
    }

    @Override
    public void setSurvey(Survey survey){
        this.survey = survey;
        multipleChoiceQuestions.forEach(multipleChoiceQuestion -> multipleChoiceQuestion.setSurvey(survey));
    }



    @Override
    public Answer answer(AnswerForm answerForm) {
        MatrixAnswer matrixAnswer = new MatrixAnswer();
        matrixAnswer.setQuestion(this);

        Map<Long , Long> answerMap = answerForm.getMatrixQuestionAnswerMap();
        matrixAnswer.setMultipleChoiceAnswers(answerMultipleChoiceQuestions(answerMap));

        return matrixAnswer;


    }

    private List<MultipleChoiceAnswer> answerMultipleChoiceQuestions( Map<Long, Long> answerMap) {
        List<MultipleChoiceAnswer> multipleChoiceAnswers = new ArrayList<>();
        for (MultipleChoiceQuestion multipleChoiceQuestion : multipleChoiceQuestions) {
            if(!answerMap.containsKey(multipleChoiceQuestion.getId()))
                continue;

            MultipleChoiceAnswer multipleChoiceAnswer = multipleChoiceQuestion.answer(answerMap.get(multipleChoiceQuestion.getId()));

            multipleChoiceAnswers.add(multipleChoiceAnswer);
        }


        return multipleChoiceAnswers;
    }

    @Override
    public QuestionAnalysis analyze() {
        return null;
    }
}
