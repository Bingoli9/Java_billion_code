package com.yanqun.cloud.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yanqun.cloud.entity.Student;

//生产者在Eureka中注册的微服务名是cloud-provider
@FeignClient(value = "cloud-provider",fallbackFactory=StudentServiceFallbackFacotry.class)
public interface StudentService {
	//增
	@RequestMapping(value = "/student/addStudent", method = RequestMethod.POST)
	boolean addStudent(Student student);
	//删
	@RequestMapping(value = "/student/deleteStudentBystuno")
	boolean deleteStudentBystuno(Integer stuno);
	//改
	@RequestMapping(value = "/student/updateStudentBystuno", method = RequestMethod.POST)
	boolean updateStudentBystuno(Student student);
	//查
	@RequestMapping(value = "/student/queryAllStudents")
	List<Student> queryAllStudents();
}
