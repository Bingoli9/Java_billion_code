package com.yanqun.cloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yanqun.cloud.entity.Student;

@Mapper
public interface StudentDao {
	// 增
	boolean addStudent(Student student);
	// 删
	boolean deleteStudentBystuno(Integer stuno);
	// 改
	boolean updateStudentBystuno(Student student);
	// 查
	List<Student> queryAllStudents();
}
