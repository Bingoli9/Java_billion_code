package com.yanqun.cloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanqun.cloud.dao.StudentDao;
import com.yanqun.cloud.entity.Student;
import com.yanqun.cloud.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentDao dao;
	@Override
	public boolean addStudent(Student student) {
		return dao.addStudent(student);
	}
	@Override
	public boolean deleteStudentBystuno(Integer stuno) {
		return dao.deleteStudentBystuno(stuno);
	}
	@Override
	public boolean updateStudentBystuno(Student student) {
		return dao.updateStudentBystuno(student);
	}
	@Override
	public List<Student> queryAllStudents() {
		return dao.queryAllStudents();
	}
}
