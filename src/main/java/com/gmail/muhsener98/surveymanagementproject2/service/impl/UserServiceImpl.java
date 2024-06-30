package com.gmail.muhsener98.surveymanagementproject2.service.impl;

import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.UserAlreadyExistsException;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.UserNotFoundException;
import com.gmail.muhsener98.surveymanagementproject2.repository.UserRepository;
import com.gmail.muhsener98.surveymanagementproject2.service.UserService;
import com.gmail.muhsener98.surveymanagementproject2.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public MyUser findUserWithoutDetails(String userId) {
        MyUser user = userRepository.findByUserId(userId);
        if(user == null)
            throw new UserNotFoundException(userId);

        return user;
    }
}
