package com.yanqun.cloud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.yanqun.cloud.entity.Student;

@RestController
public class StudentController
{
	Logger logger = LoggerFactory.getLogger(StudentController.class.getName());
	//服务提供者的URL地址前缀
//	private static final String URL_PREFIX = "http://localhost:8888";
	private static final String URL_PREFIX = "http://cloud-provider";
	//从Spring IoC容器中获取远程访问微服务的RestTemplate组件
	@Autowired
	private RestTemplate restTemplate;
	//远程访问服务提供者的addStudent()方法
	@RequestMapping(value = "/consumer/student/addStudent")
	public boolean addStudent(Student student)
	{
		 ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(URL_PREFIX + "/student/addStudent",student,  Boolean.class);
		 return responseEntity.getBody();
	}
	//远程访问服务提供者的deleteStudentBystuno()方法
	@RequestMapping(value = "/consumer/student/deleteStudentBystuno/{stuno}")
	public boolean deleteStudentBystuno(@PathVariable("stuno") Integer stuno)
	{
		return restTemplate.getForObject(URL_PREFIX + "/student/deleteStudentBystuno/" + stuno, Boolean.class);
	}
	//远程访问服务提供者的updateStudentBystuno()方法
	@RequestMapping(value = "/consumer/student/updateStudentBystuno")
	public boolean updateStudentBystuno(Student student)
	{
		System.out.println("1111111111111");
		return restTemplate.postForObject(URL_PREFIX + "/student/updateStudentBystuno", student, Boolean.class);
	}
	//远程访问服务提供者的queryAllStudents()方法
	@RequestMapping(value = "/consumer/student/queryAllStudents")
	public List<Student> queryAllStudents()
	{
		logger.info( "==queryAllStudents=="); 
		return restTemplate.getForObject(URL_PREFIX + "/student/queryAllStudents", List.class);
	}
}