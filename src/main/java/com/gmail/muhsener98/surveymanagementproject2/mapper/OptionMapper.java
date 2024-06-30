package com.gmail.muhsener98.surveymanagementproject2.mapper;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.Option;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question.OptionCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question.OptionRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OptionMapper {

    OptionMapper INSTANCE = Mappers.getMapper(OptionMapper.class);

    Option toEntity(OptionCreationForm optionCreationForm);

    OptionRest toRest(Option option);
}
