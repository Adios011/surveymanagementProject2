package com.gmail.muhsener98.surveymanagementproject2.mapper;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.MultipleChoiceQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.OpenEndedQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.RatingScaleQuestion;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question.MultipleChoiceQuestionCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question.OpenEndedQuestionCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question.QuestionCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question.RatingScaleQuestionCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question.MultipleChoiceQuestionRest;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question.OpenEndedQuestionRest;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question.QuestionRest;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question.RatingScaleQuestionRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {OptionMapper.class})
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);


    default Question toEntity(QuestionCreationForm questionCreationForm) {
        if (questionCreationForm instanceof MultipleChoiceQuestionCreationForm multipleChoiceQuestionCreationForm)
            return toEntity(multipleChoiceQuestionCreationForm);
        else if (questionCreationForm instanceof OpenEndedQuestionCreationForm openEndedQuestionCreationForm)
            return toEntity(openEndedQuestionCreationForm);
        else if(questionCreationForm instanceof RatingScaleQuestionCreationForm ratingScaleQuestionCreationForm)
            return toEntity(ratingScaleQuestionCreationForm);
        else
            throw new IllegalArgumentException("Unknown subclass type: " + questionCreationForm.getClass().getName());
    }


    MultipleChoiceQuestion toEntity(MultipleChoiceQuestionCreationForm multipleChoiceQuestionCreationForm);
    OpenEndedQuestion toEntity(OpenEndedQuestionCreationForm openEndedQuestionCreationForm);
    RatingScaleQuestion toEntity(RatingScaleQuestionCreationForm ratingScaleQuestionCreationForm);


    MultipleChoiceQuestionRest toRest(MultipleChoiceQuestion multipleChoiceQuestion);
    OpenEndedQuestionRest toRest(OpenEndedQuestion openEndedQuestion);
    RatingScaleQuestionRest toRest(RatingScaleQuestion ratingScaleQuestion);


    default QuestionRest toRest(Question question){
        if(question instanceof  MultipleChoiceQuestion mcq )
            return toRest(mcq);
        else if (question instanceof OpenEndedQuestion oeq)
            return toRest(oeq);
        else if(question instanceof RatingScaleQuestion ratingScaleQuestion)
            return toRest(ratingScaleQuestion);
        else
            throw new IllegalArgumentException("Unknown subclass type " + question.getClass().getName())    ;
    }
}
