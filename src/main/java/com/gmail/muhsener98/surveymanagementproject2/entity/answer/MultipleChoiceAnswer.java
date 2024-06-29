package com.gmail.muhsener98.surveymanagementproject2.entity.answer;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
//@Table(name = "multiple_choice_answers")
@DiscriminatorValue("multiple_choice")
public class MultipleChoiceAnswer extends Answer{
}
