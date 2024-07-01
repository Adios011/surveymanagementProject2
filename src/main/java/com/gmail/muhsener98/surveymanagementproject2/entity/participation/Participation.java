package com.gmail.muhsener98.surveymanagementproject2.entity.participation;

import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.question.Question;
import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.participation.AnswerForm;
import jakarta.persistence.*;
import jakarta.servlet.http.Part;

import java.util.List;
import java.util.Map;

@NamedQuery(name = "find_All_Surveys_Participated_By_User_Without_Associations",
        query = "Select new com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey " +
                "(s.id, s.surveyId, s.title, s.description, s.closeDate , s.numberOfParticipants) " +
                "FROM Participation p " +
                "JOIN p.survey s " +
                "JOIN p.user u " +
                "WHERE u = :user")
@Entity
@Table(name = "participations")
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private MyUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Survey survey;

    @OneToMany(mappedBy = "participation", cascade = {CascadeType.ALL})
    private List<Answer> answers;

    public Participation() {

    }


    public Participation(Survey survey, List<Answer> answers) {
        this(null, survey, answers);
    }

    public Participation(MyUser user, Survey survey, List<Answer> answers) {
        this.user = user;
        this.survey = survey;
        this.answers = answers;

        for (Answer answer : answers) {
            answer.setParticipation(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void edit(Map<Long, AnswerForm> answerFormMap) {
        for (Answer answer : answers) {
            Question question = answer.getQuestion();
            AnswerForm answerForm = answerFormMap.get(question.getId());

            if (answerForm == null)
                continue;

            answer.update(answerForm);

        }
    }
}

