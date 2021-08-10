package com.yanqun.demo.mapper;

import java.util.List;

import com.yanqun.entity.Student;
public interface StudentMapper {
	public boolean addStudent(Student student);
	public boolean deleteStudentByStuno(int stuno);
	public boolean updateStudentByStuno(Student student);
	public List<Student> queryStudents() ;
}
