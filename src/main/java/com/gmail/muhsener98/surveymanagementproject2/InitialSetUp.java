package com.gmail.muhsener98.surveymanagementproject2;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialSetUp {



    @EventListener
    @Transactional
    public void onApplicationReady(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("****APPLICATION HAS STARTED****");
    }
}