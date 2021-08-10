package com.yanqun.cloud.service;

import java.util.List;

import com.yanqun.cloud.entity.Student;

public interface StudentService {
	boolean addStudent(Student student) ;
	boolean deleteStudentBystuno(Integer stuno);
	boolean updateStudentBystuno(Student student);
	List<Student> queryAllStudents();
}
