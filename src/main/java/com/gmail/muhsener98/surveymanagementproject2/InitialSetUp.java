package com.gmail.muhsener98.surveymanagementproject2;

import com.gmail.muhsener98.surveymanagementproject2.entity.authorization.Role;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.repository.RoleRepository;
import com.gmail.muhsener98.surveymanagementproject2.repository.UserRepository;
import com.gmail.muhsener98.surveymanagementproject2.shared.authorization.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitialSetUp {



    @EventListener
    @Transactional
    public void onApplicationReady(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("****APPLICATION HAS STARTED****");
    }
}