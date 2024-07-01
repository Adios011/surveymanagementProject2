package com.gmail.muhsener98.surveymanagementproject2.mapper;

import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.participation.ParticipationRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {SurveyMapper.class , AnswerMapper.class})
public interface ParticipationMapper {
    ParticipationMapper INSTANCE = Mappers.getMapper(ParticipationMapper.class);

    ParticipationRest toRest(Participation participation);
}
