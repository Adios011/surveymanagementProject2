package com.gmail.muhsener98.surveymanagementproject2.mapper;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.*;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question.*;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

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
        else if (questionCreationForm instanceof MatrixQuestionCreationForm matrixQuestionCreationForm)
            return toEntity(matrixQuestionCreationForm);
        else
            throw new IllegalArgumentException("Unknown subclass type: " + questionCreationForm.getClass().getName());
    }


    MultipleChoiceQuestion toEntity(MultipleChoiceQuestionCreationForm multipleChoiceQuestionCreationForm);
    OpenEndedQuestion toEntity(OpenEndedQuestionCreationForm openEndedQuestionCreationForm);
    RatingScaleQuestion toEntity(RatingScaleQuestionCreationForm ratingScaleQuestionCreationForm);
    MatrixQuestion toEntity(MatrixQuestionCreationForm matrixQuestionCreationForm);


    MultipleChoiceQuestionRest toRest(MultipleChoiceQuestion multipleChoiceQuestion);
    OpenEndedQuestionRest toRest(OpenEndedQuestion openEndedQuestion);
    RatingScaleQuestionRest toRest(RatingScaleQuestion ratingScaleQuestion);

    default MatrixQuestionRest toRest(MatrixQuestion matrixQuestion){
        MatrixQuestionRest rest = new MatrixQuestionRest();
        rest.setId(matrixQuestion.getId());
        rest.setQuestionText(matrixQuestion.getQuestionText());

        List<Long > ids = new ArrayList<>();
        for (MultipleChoiceQuestion multipleChoiceQuestion : matrixQuestion.getMultipleChoiceQuestions()) {
            ids.add(multipleChoiceQuestion.getId());
        }
        rest.setInnerQuestionIds(ids);
        return rest;
    }


    default QuestionRest toRest(Question question){
        if(question instanceof  MultipleChoiceQuestion mcq )
            return toRest(mcq);
        else if (question instanceof OpenEndedQuestion oeq)
            return toRest(oeq);
        else if(question instanceof RatingScaleQuestion ratingScaleQuestion)
            return toRest(ratingScaleQuestion);
        else if(question instanceof MatrixQuestion matrixQuestion)
            return toRest(matrixQuestion);
        else
            throw new IllegalArgumentException("Unknown subclass type " + question.getClass().getName())    ;
    }
}
