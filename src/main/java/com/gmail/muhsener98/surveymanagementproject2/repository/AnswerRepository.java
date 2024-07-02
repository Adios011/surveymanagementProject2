package com.gmail.muhsener98.surveymanagementproject2.repository;

import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.MultipleChoiceAnswer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.OpenEndedAnswer;
import com.gmail.muhsener98.surveymanagementproject2.entity.participation.Participation;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AnswerRepository extends JpaRepository<Answer,Long>  {

//    Answer findById(Long id );
//    Answer save(Answer entity);

    @Query(name = "load_multiple_choice_answer_with_all_associations_by_participation")
    List<MultipleChoiceAnswer> findAllMultipleChoiceAnswersWithQuestionByParticipation(@Param("participation")Participation participation);


}
