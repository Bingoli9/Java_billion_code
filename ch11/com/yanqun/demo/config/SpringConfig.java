package com.yanqun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yanqun.dao.StudentDao;
import com.yanqun.service.StudentService;

@Configuration
public class SpringConfig {
	@Bean
	public StudentService stuService(){
		StudentService stuService = new StudentService();
		StudentDao stuDao = new StudentDao();
		stuService.setStudentDao(stuDao);
		return stuService ;
	}
	
}
