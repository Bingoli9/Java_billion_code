package com.yanqun.cloud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.yanqun.cloud.entity.Student;

import feign.hystrix.FallbackFactory;
@Component
public class StudentServiceFallbackFacotry implements FallbackFactory<StudentService>{
	//批量处理StudentService接口中的所有方法
	@Override
	public StudentService create(Throwable ex) {
		return new StudentService() {
			//当StudentService中的addStudent()发生异常时，自动跳转到该方法处理
			@Override
			public boolean addStudent(Student student) {
				System.out.println("增加失败...");
				return false;
			}
			//当StudentService中的deleteStudentBystuno()发生异常时，自动跳转到该方法处理
			@Override
			public boolean deleteStudentBystuno(Integer stuno) {
				System.out.println("删除失败...");
				return false;
			}
			//当StudentService中的updateStudentBystuno()发生异常时，自动跳转到该方法处理
			@Override
			public boolean updateStudentBystuno(Student student) {
				System.out.println("更新失败...");
				return false;
			}
			//当StudentService中的queryAllStudents()发生异常时，自动跳转到该方法处理
			@Override
			public List<Student> queryAllStudents() {
				List<Student> stus = new ArrayList<>();
				stus.add(new Student(0,"no students","no databases"));
				return stus ; 
			}
		};
	}
}
