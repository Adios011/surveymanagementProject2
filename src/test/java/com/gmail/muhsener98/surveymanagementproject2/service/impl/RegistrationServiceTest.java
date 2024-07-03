package com.gmail.muhsener98.surveymanagementproject2.service.impl;

import com.gmail.muhsener98.surveymanagementproject2.entity.authorization.Role;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.exceptions.UserAlreadyExistsException;
import com.gmail.muhsener98.surveymanagementproject2.repository.UserRepository;
import com.gmail.muhsener98.surveymanagementproject2.service.RegistrationService;
import com.gmail.muhsener98.surveymanagementproject2.shared.dto.UserDto;
import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.user.RegistrationForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegistrationServiceImpl registrationService;


    private MyUser user = new MyUser();
    private final String userId = "userId";
    private final String firstName = "Muhammet";
    private final String lastName = "tester";
    private final String encryptedPassword = "password";
    private final String email = "test@gmail.com";
    private final Date birthDate = Date.from(Instant.EPOCH);
    private final List<Role> roles = new ArrayList<>(List.of(new Role("USER")));

    @BeforeEach
    public void setUpUser() {
        MockitoAnnotations.openMocks(this);
        user = MyUser.builder()
                .setUserId(userId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEncryptedPassword(encryptedPassword)
                .setEmail(email)
                .setBirthDate(birthDate)
                .setRoles(roles)
                .build();
    }


    @Test
    public void registrationService_registerUser_returnsUserDto() {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setEmail("test@gmail.com");
        registrationForm.setPassword("password");

        when(userRepository.save(any(MyUser.class))).thenReturn(user);

        UserDto result = registrationService.registerUser(registrationForm);

        assertNotNull(result);
        assertEquals("test@gmail.com", result.getEmail());
        assertEquals("password", result.getEncryptedPassword());

        verify(userRepository, times(1)).save(any(MyUser.class));
    }

    @Test
    public void registrationService_registerUser_UserAlreadyExists() {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setEmail("existing@gmail.com");

        when(userRepository.existsByEmailIgnoreCase(registrationForm.getEmail())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class ,
                () -> registrationService.registerUser(registrationForm));

        verify(userRepository,never()).save(any(MyUser.class));
        verify(userRepository,times(1)).existsByEmailIgnoreCase(anyString());

    }


    @Test
    public void registrationService_generateUserId_returnsStringOfLength30IfLengthIsLessThan30(){
        String result = registrationService.generateUserId(1);

        assertEquals(result.length() , 30);
    }

    @Test
    public void registrationService_generateUserId_returnsStringOfLength255IfLengthIsGreaterThan255(){
        String result = registrationService.generateUserId(1123123);

        assertEquals(result.length() , 255);
    }


}
