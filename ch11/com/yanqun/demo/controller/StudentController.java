package com.yanqun.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yanqun.demo.mapper.StudentMapper;
import com.yanqun.entity.Student;

@RestController
public class StudentController {
	@Autowired
	StudentMapper studentMapper ;
	@RequestMapping("/addStudent")
	public boolean addStudent(Student student){
		return studentMapper.addStudent(student);
	}
	@RequestMapping("/deleteStudentStuno")
	public boolean deleteStudentByStuno(int stuno){
		return studentMapper.deleteStudentByStuno(stuno);
	}
	@RequestMapping("/updateStudentStuno")
	public boolean updateStudentByStuno(Student student){
		return studentMapper.updateStudentByStuno(student);
	}
	@RequestMapping("/queryStudents")
	public List<Student> queryStudents(){
		return studentMapper.queryStudents();
	}
	
	@RequestMapping("/queryAStudent")
	public Student queryAStudent(){
		Student student=new Student();
		student.setStuno(101);
		student.setStuname("颜群");
		student.setGradeid(1);
	      return student;
	}
}
