package com.gmail.muhsener98.surveymanagementproject2.entity.answer;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.InnerQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.MultipleChoiceQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.BatchSize;

import java.util.List;
import java.util.Map;

@Entity
@DiscriminatorValue("matrix_answer")
public class MatrixAnswer extends Answer {

    @OneToMany(mappedBy = "matrixAnswer", cascade = {CascadeType.ALL} )
    @BatchSize(size = 10)
    private List<InnerQuestionAnswer> innerQuestionAnswers;




    public List<InnerQuestionAnswer> getInnerQuestionAnswers() {
        return innerQuestionAnswers;
    }

    public void setInnerQuestionAnswers(List<InnerQuestionAnswer> innerQuestionAnswers) {
        this.innerQuestionAnswers = innerQuestionAnswers;
        for(InnerQuestionAnswer innerQuestionAnswer : innerQuestionAnswers)
            innerQuestionAnswer.setMatrixAnswer(this);
    }

    @Override
    public void setParticipation(Participation participation) {
        this.participation = participation;
        innerQuestionAnswers.forEach(multipleChoiceAnswer -> multipleChoiceAnswer.setParticipation(participation));
    }

    @Override
    public void delete() {
        innerQuestionAnswers.forEach(InnerQuestionAnswer::delete);
        innerQuestionAnswers = null;
    }

    @Override
    public void update(AnswerForm answerForm) {
        Map<Long,Long> mcqAnswers = answerForm.getMatrixQuestionAnswerMap();
        for (InnerQuestionAnswer innerQuestionAnswer : innerQuestionAnswers) {
            InnerQuestion question = (InnerQuestion) innerQuestionAnswer.question;
            if(!mcqAnswers.containsKey(question.getId()))
                continue;

            Long optionId = mcqAnswers.get(question.getId());
            innerQuestionAnswer.update(optionId);
        }
    }

    @Override
    public String getAnswerText() {
        return "This is matrix question.";
    }
}
