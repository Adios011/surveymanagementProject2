package com.gmail.muhsener98.surveymanagementproject2.service;


import com.gmail.muhsener98.surveymanagementproject2.entity.survey.Survey;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.shared.dto.UserDto;

public interface UserService {


    MyUser findUserWithoutDetails(String userId);



}
