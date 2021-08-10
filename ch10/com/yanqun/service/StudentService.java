package com.yanqun.service;

import com.yanqun.dao.StudentDao;

public class StudentService {
	private StudentDao studentDao;
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	public StudentDao getStudentDao() {
		return studentDao;
	}
	
	//...
}
