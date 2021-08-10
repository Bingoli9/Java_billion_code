package org.students.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.students.mapper.StudentMapper;
import org.students.pojo.Student;
import org.students.service.StudentService;

import com.alibaba.dubbo.config.annotation.Service;

@Service//注意，此@Service接口是dubbo提供的，在com.alibaba.dubbo.config.annotation.Service包中。与Spring提供的@Service重名，要注意区分二者
public class StudentServiceImpl implements StudentService {
	//Service依赖Dao(MyBatis的Mapper)
	@Autowired 
	@Qualifier("studentMapper")
	private StudentMapper studentMapper ; 
	@Override
	public void addStudent(Student student) {
		studentMapper.addStudent(student);//if
	}
	@Override
	public Student queryStudentByStuNo(int stuNo) {
		return studentMapper.queryStudentByStuno(stuNo) ;
	}
}
