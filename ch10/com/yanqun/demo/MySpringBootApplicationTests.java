package com.yanqun.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.yanqun.dao.StudentDao;
import com.yanqun.demo.entity.Student;
import com.yanqun.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringBootApplicationTests {
	Logger logger = LoggerFactory.getLogger(MySpringBootApplicationTests.class);
	@Autowired
	Student student ;
	//获取SpringIOC容器
	@Autowired
	ApplicationContext context ;
	@Test
	public void testLog() {// 日志级别
		logger.trace("trace********");
		logger.debug("debug********");
		logger.info("info*******");
		logger.warn("warn******");
		logger.error("error****");
	}
	@Test
	public void testIOC() {
		StudentService studentService = (StudentService)context.getBean("studenService") ;
		StudentDao  stuentDao = studentService.getStudentDao();
		System.out.println(stuentDao);
	}
	
	@Test
	public void testDI() {
		System.out.println(student);
	}
}
