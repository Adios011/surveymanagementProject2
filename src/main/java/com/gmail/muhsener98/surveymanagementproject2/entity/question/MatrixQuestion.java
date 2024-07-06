package com.gmail.muhsener98.surveymanagementproject2.entity.question;

import com.gmail.muhsener98.surveymanagementproject2.analysis.questions.MatrixQuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.analysis.questions.MultipleChoiceQuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.analysis.questions.QuestionAnalysis;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.InnerQuestionAnswer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.MatrixAnswer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.MultipleChoiceAnswer;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "matrix_questions")
public class MatrixQuestion extends Question{

    @OneToMany(mappedBy = "matrixQuestion" , cascade = {CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REMOVE} )
    private List<InnerQuestion> innerQuestions ;

//    public List<MultipleChoiceQuestion> getMultipleChoiceQuestions() {
//        return multipleChoiceQuestions;
//    }
//
//    public void setMultipleChoiceQuestions(List<MultipleChoiceQuestion> multipleChoiceQuestions) {
//        this.innerQuestions = multipleChoiceQuestions;
//        for (MultipleChoiceQuestion multipleChoiceQuestion : multipleChoiceQuestions) {
//            multipleChoiceQuestion.setMatrixQuestion(this);
//
//        }
//    }

    public List<InnerQuestion> getInnerQuestions() {
        return innerQuestions;
    }

    public void setInnerQuestions(List<InnerQuestion> innerQuestions) {
        this.innerQuestions = innerQuestions;
        for (InnerQuestion innerQuestion : innerQuestions) {
            innerQuestion.setMatrixQuestion(this);
        }
    }

    @Override
    public void setSurvey(Survey survey){
        this.survey = survey;
        innerQuestions.forEach(innerQuestion -> innerQuestion.setSurvey(survey));
    }



    @Override
    public MatrixAnswer answer(AnswerForm answerForm) {
        MatrixAnswer matrixAnswer = new MatrixAnswer();
        matrixAnswer.setQuestion(this);

        Map<Long , Long> answerMap = answerForm.getMatrixQuestionAnswerMap();
        if(answerForm == null)
            throw new IllegalArgumentException("null matrix-question answer map for question " + id );


        matrixAnswer.setInnerQuestionAnswers(answerInnerQuestions(answerMap));

        return matrixAnswer;


    }

    private List<InnerQuestionAnswer> answerInnerQuestions( Map<Long, Long> answerMap) {
        List<InnerQuestionAnswer> innerQuestionAnswers = new ArrayList<>();
        for (InnerQuestion innerQuestion : innerQuestions) {
            if(!answerMap.containsKey(innerQuestion.getId()))
                continue;

            InnerQuestionAnswer innerQuestionAnswer = innerQuestion.answer(answerMap.get(innerQuestion.getId()));

            innerQuestionAnswers.add(innerQuestionAnswer);
        }


        return innerQuestionAnswers;
    }

    @Override
    public MatrixQuestionAnalysis analyze() {
        MatrixQuestionAnalysis matrixQuestionAnalysis = new MatrixQuestionAnalysis(id , questionText);
        matrixQuestionAnalysis.setInnerQuestionIds(getInnerQuestionIds());
        return matrixQuestionAnalysis;
    }


    private List<Long> getInnerQuestionIds(){
        List<Long> ids = new ArrayList<>();
        for (InnerQuestion innerQuestion : innerQuestions) {
            ids.add(innerQuestion.getId());
        }
        return ids;
    }


}
