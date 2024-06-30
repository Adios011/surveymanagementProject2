package com.gmail.muhsener98.surveymanagementproject2.mapper;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.MultipleChoiceQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.OpenEndedQuestion;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question.MultipleChoiceQuestionCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question.OpenEndedQuestionCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question.QuestionCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question.MultipleChoiceQuestionRest;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question.OpenEndedQuestionRest;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question.QuestionRest;
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
        else
            throw new IllegalArgumentException("Unknown subclass type: " + questionCreationForm.getClass().getName());
    }


    MultipleChoiceQuestion toEntity(MultipleChoiceQuestionCreationForm multipleChoiceQuestionCreationForm);
    OpenEndedQuestion toEntity(OpenEndedQuestionCreationForm openEndedQuestionCreationForm);


    MultipleChoiceQuestionRest toRest(MultipleChoiceQuestion multipleChoiceQuestion);
    OpenEndedQuestionRest toRest(OpenEndedQuestion openEndedQuestion);


    default QuestionRest toRest(Question question){
        if(question instanceof  MultipleChoiceQuestion mcq )
            return toRest(mcq);
        else if (question instanceof OpenEndedQuestion oeq)
            return toRest(oeq);
        else
            throw new IllegalArgumentException("Unknown subclass type " + question.getClass().getName())    ;
    }
}
