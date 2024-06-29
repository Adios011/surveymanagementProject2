package com.gmail.muhsener98.surveymanagementproject2.service.impl;

import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.repository.UserRepository;
import com.gmail.muhsener98.surveymanagementproject2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public MyUser getUser(String name) {

        System.out.println("****findAll()*****");
        List<MyUser> users = userRepository.findAll();
        System.out.println("****findAll()*****");


        users.forEach(user -> System.out.println(user.getRoles()));






        return null;


    }
}
