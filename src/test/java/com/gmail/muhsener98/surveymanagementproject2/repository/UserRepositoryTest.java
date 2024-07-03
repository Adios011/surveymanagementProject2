package com.gmail.muhsener98.surveymanagementproject2.repository;

import com.gmail.muhsener98.surveymanagementproject2.entity.authorization.Role;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import jakarta.persistence.Temporal;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private MyUser user;


    @BeforeEach
    public void buildUser(){
         user = MyUser.builder()
                .setUserId("userId")
                .setFirstName("Muhammet")
                .setLastName("Şener")
                .setEncryptedPassword("password")
                .setEmail("email")
                .setBirthDate(new Date())
                 .setRoles(List.of(new Role("ROLE_USER")))
                 .build();
    }

    @Test
    public void userRepository_save_ReturnsSavedUser(){

        //Arrange

        //Act

        MyUser savedUser = userRepository.save(user);

        //Assert

        assertNotNull(savedUser);
        assertEquals(savedUser.getId() , 1L);
        assertEquals(user.getFirstName() , savedUser.getFirstName());

    }


    @Test
    public void userRepository_findByUserId_returnsUser(){
        MyUser savedUser = userRepository.save(user);

        MyUser userReceived = userRepository.findByUserId(user.getUserId());


        assertEquals(savedUser.getFirstName() , userReceived.getFirstName());
        assertEquals(savedUser.getId() , userReceived.getId());


    }


}
