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

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @EventListener
    @Transactional
    public void onApplicationReady(ApplicationReadyEvent applicationReadyEvent) {
        Role userRole = createAndSaveRole(Roles.USER_ROLE.name());
        Role adminRole = createAndSaveRole(Roles.ADMIN_ROLE.name());


        List<MyUser> users = new ArrayList<>();
        MyUser admin = new MyUser("admin");
        admin.setRoles(List.of(adminRole));
        users.add(admin);

        for(int i = 0 ; i < 20 ; i++){
            MyUser user = new MyUser("User " + i);
            user.setRoles(List.of(userRole));
            users.add(user);
        }

        addAll(users);



    }

    @Transactional
    private MyUser createAndSaveUser(String name, List<Role> roles) {
        MyUser user = new MyUser(name);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Transactional
    private void  addAll(List<MyUser> users){
        userRepository.saveAllAndFlush(users);
    }

    @Transactional
    private Role createAndSaveRole(String name) {
        Role role = new Role(name);
        return roleRepository.save(role);
    }
}
