package com.gmail.muhsener98.surveymanagementproject2.service;

import com.gmail.muhsener98.surveymanagementproject2.shared.dto.UserDto;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.user.RegistrationForm;

public interface RegistrationService {

    UserDto registerUser(RegistrationForm registrationForm);
}
