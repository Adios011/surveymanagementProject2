package com.gmail.muhsener98.surveymanagementproject2;

import com.gmail.muhsener98.surveymanagementproject2.entity.answer.Answer;
import com.gmail.muhsener98.surveymanagementproject2.entity.answer.OpenEndedAnswer;
import com.gmail.muhsener98.surveymanagementproject2.entity.user.MyUser;
import com.gmail.muhsener98.surveymanagementproject2.repository.AnswerRepository;
import com.gmail.muhsener98.surveymanagementproject2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication( exclude = SecurityAutoConfiguration.class)
public class App  /*extends SpringBootServletInitializer */{

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
//		return applicationBuilder.sources(App.class);
//	}


	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}


	@Bean(name = "passwordEncoder")
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
