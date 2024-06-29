package com.gmail.muhsener98.surveymanagementproject2.repository;

import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<MyUser,Long> {



//    @EntityGraph(attributePaths = {"roles"} , type = EntityGraph.EntityGraphType.LOAD)
    List<MyUser> findAll();





}

