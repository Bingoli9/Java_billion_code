package com.yanqun.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.yanqun.cloud.entity.Student;
import com.yanqun.cloud.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;

	// 增
		@RequestMapping(value = "/consumerfeign/student/addStudent", method = RequestMethod.POST)
		boolean addStudent(Student student){
			return studentService.addStudent(student) ;
		}
		//删
		@RequestMapping(value = "/consumerfeign/student/deleteStudentBystuno")
		boolean deleteStudentBystuno(Integer stuno){
			return studentService.deleteStudentBystuno(stuno);
		}
		//改
		@RequestMapping(value = "/consumerfeign/student/updateStudentBystuno", method = RequestMethod.POST)
		boolean updateStudentBystuno(Student student){
			return studentService.updateStudentBystuno(student);
		}
		//查
		@RequestMapping(value = "/consumerfeign/student/queryAllStudents")
		List<Student> queryAllStudents(){
			return studentService.queryAllStudents();
		}
}