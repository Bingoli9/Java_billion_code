package org.students.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.students.pojo.Student;
import org.students.service.StudentService;

import com.alibaba.dubbo.config.annotation.Reference;

@Controller
@RequestMapping("controller")
public class StudentController {
	// 使用dubbo提供的@Reference，远程调用服务提供方的StudentService接口
	@Reference
	private StudentService studentService;
	@RequestMapping("queryStudentByNo")
	public ModelAndView queryStudentByNo() {
		ModelAndView mv = new ModelAndView("success");
		Student student = studentService.queryStudentByStuNo(1);
		mv.addObject("student", student);// request域
		return mv;
	}
	@RequestMapping("addStudent")
	public String addStudent(Map<String,Object> map) {
		Student student = new Student(1, "颜群", 30);
		studentService.addStudent(student);
		map.put("student", student);
		return "success";
	}
}
