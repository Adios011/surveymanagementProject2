package com.gmail.muhsener98.surveymanagementproject2.mapper;

import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.answer.AnswerRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {QuestionMapper.class })
public interface AnswerMapper {

    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);


    AnswerRest toRest(Answer answer);
}
