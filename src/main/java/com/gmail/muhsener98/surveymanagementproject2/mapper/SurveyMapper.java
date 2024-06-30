package com.gmail.muhsener98.surveymanagementproject2.mapper;

import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.question.QuestionCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.survey.SurveyCreationForm;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.question.QuestionRest;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.response.survey.SurveyRest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {QuestionMapper.class})
public interface SurveyMapper {

    SurveyMapper INSTANCE = Mappers.getMapper(SurveyMapper.class);

//    @Mapping(dateFormat ="dd.MM.yyyy" , target = "closeDate" , source = "closeDate")
    Survey toEntity(SurveyCreationForm surveyCreationForm);

    SurveyRest toRest(Survey survey);

    List<QuestionRest> toRest (List<Question> questions);
}
