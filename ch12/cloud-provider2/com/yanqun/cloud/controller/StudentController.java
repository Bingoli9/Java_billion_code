package com.yanqun.cloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yanqun.cloud.entity.Student;
import com.yanqun.cloud.service.StudentService;

@RestController
public class StudentController
{
	@Autowired
	private DiscoveryClient client ; 
	@Autowired
	private StudentService service;
	//增
	@RequestMapping(value = "/student/addStudent", method = RequestMethod.POST)
	public boolean addStudent( @RequestBody Student student)
	{
		return service.addStudent(student);
	}
	//删
	@RequestMapping(value = "/student/deleteStudentBystuno/{stuno}")
	public boolean deleteStudentBystuno(@PathVariable("stuno") Integer stuno)
	{
		return service.deleteStudentBystuno(stuno);
	}
	//改
	@RequestMapping(value = "/student/updateStudentBystuno", method = RequestMethod.POST)
	public boolean updateStudentBystuno(@RequestBody Student student)
	{
		System.out.println(student);
		return service.updateStudentBystuno(student);
	}
	//查
	@RequestMapping(value = "/student/queryAllStudents")
	public List<Student> queryAllStudents()
	{
		return service.queryAllStudents();
	}
	
	@RequestMapping(value = "/student/discovery")
	public Object discovery()
	{
		StringBuffer sb = new StringBuffer();
		client.getServices().forEach((serviceId)->{
			client.getInstances(serviceId).forEach((instance)->{
				sb.append("serviceId:")
				.append( instance.getServiceId())
				.append("<br/>host:")
				.append(instance.getHost())
				.append("<br/>port:")
				.append( instance.getPort())
				.append("<br/>metadata:")
				.append(instance.getMetadata())
				.append("<br/>uri:")
				.append(instance.getUri());
			});
		});
		return sb;
	}
}
