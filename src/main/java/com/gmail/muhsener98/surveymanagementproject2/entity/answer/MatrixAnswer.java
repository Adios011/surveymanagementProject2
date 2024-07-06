package com.gmail.muhsener98.surveymanagementproject2.entity.answer;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.MultipleChoiceQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Map;

@Entity
@DiscriminatorValue("matrix_answer")
public class MatrixAnswer extends Answer {

    @OneToMany(mappedBy = "matrixAnswer", cascade = {CascadeType.ALL})
    private List<MultipleChoiceAnswer> multipleChoiceAnswers;


    public List<MultipleChoiceAnswer> getMultipleChoiceAnswers() {
        return multipleChoiceAnswers;
    }

    public void setMultipleChoiceAnswers(List<MultipleChoiceAnswer> multipleChoiceAnswers) {
        this.multipleChoiceAnswers = multipleChoiceAnswers;
        for (MultipleChoiceAnswer multipleChoiceAnswer : multipleChoiceAnswers) {
            multipleChoiceAnswer.setMatrixAnswer(this);
        }
    }

    @Override
    public void setParticipation(Participation participation) {
        this.participation = participation;
        multipleChoiceAnswers.forEach(multipleChoiceAnswer -> multipleChoiceAnswer.setParticipation(participation));
    }

    @Override
    public void delete() {
        multipleChoiceAnswers.forEach(MultipleChoiceAnswer::delete);
        multipleChoiceAnswers = null;
    }

    @Override
    public void update(AnswerForm answerForm) {
        Map<Long,Long> mcqAnswers = answerForm.getMatrixQuestionAnswerMap();
        for (MultipleChoiceAnswer multipleChoiceAnswer : multipleChoiceAnswers) {
            MultipleChoiceQuestion question = (MultipleChoiceQuestion) multipleChoiceAnswer.question;
            if(!mcqAnswers.containsKey(question.getId()))
                continue;

            Long optionId = mcqAnswers.get(question.getId());
            multipleChoiceAnswer.update(optionId);
        }
    }

    @Override
    public String getAnswerText() {
        return "This is matrix question.";
    }
}
