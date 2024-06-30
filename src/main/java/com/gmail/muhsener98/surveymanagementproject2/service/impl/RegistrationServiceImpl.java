package com.gmail.muhsener98.surveymanagementproject2.service.impl;

import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.UserAlreadyExistsException;
import com.gmail.muhsener98.surveymanagementproject2.repository.UserRepository;
import com.gmail.muhsener98.surveymanagementproject2.service.RegistrationService;
import com.gmail.muhsener98.surveymanagementproject2.shared.AlphanumericStringGenerator;
import com.gmail.muhsener98.surveymanagementproject2.shared.dto.UserDto;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.user.RegistrationForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

    private static final  int MAX_USER_ID_LENGTH = 255 ;
    private static final  int MIN_USER_ID_LENGTH = 30 ;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDto registerUser(RegistrationForm registrationForm){
        validateUserRegistration(registrationForm.getEmail());

       MyUser userToBeSaved =  prepareUserForRegistration(registrationForm);

       MyUser savedUser = userRepository.save(userToBeSaved);

       return convertSavedUserToDto(savedUser);

    }

    private MyUser prepareUserForRegistration(RegistrationForm registrationForm){
        MyUser user = new MyUser();
        BeanUtils.copyProperties(registrationForm , user);
        user.setEncryptedPassword(encodePassword(registrationForm.getPassword()));
        //TODO : I set "a" temporarily.
        user.setUserId("a");
        return user;
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }


    private void validateUserRegistration(String email){
        if(email == null)
            throw new IllegalArgumentException("null email");

        if(userRepository.existsByEmailIgnoreCase(email))
            throw new UserAlreadyExistsException(email);
    }

    private UserDto convertSavedUserToDto(MyUser user){
        UserDto savedUserDetails = new UserDto();
        BeanUtils.copyProperties(user , savedUserDetails);
        return savedUserDetails;
    }


    public static String generateUserId(int length){
        if(length < MIN_USER_ID_LENGTH)
            length = MIN_USER_ID_LENGTH;
        else if(length > MAX_USER_ID_LENGTH)
            length = MAX_USER_ID_LENGTH;

        return AlphanumericStringGenerator.generate(length);
    }


}
