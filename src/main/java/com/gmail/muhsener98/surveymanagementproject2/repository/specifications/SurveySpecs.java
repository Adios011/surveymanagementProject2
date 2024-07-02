package com.gmail.muhsener98.surveymanagementproject2.repository.specifications;

import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class SurveySpecs {

    public static Specification<Survey> isOpen(){
        return ((root, query, criteriaBuilder) -> {
            Date now = new Date();
            return criteriaBuilder.greaterThan(root.get("closeDate") , now);
        });
    }

    public static Specification<Survey> isClosed() {
        return ((root, query, criteriaBuilder) -> {
           Date now = new Date();
           return criteriaBuilder.lessThan(root.get("closeDate") , now);
        });
    }
}
