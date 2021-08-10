package com.yanqun.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.yanqun.entity.Student;

@Service
public class StudentService {
	@Async
	public void showStudents() throws InterruptedException{
		//模拟显示100个学生
		for(int i=0;i<10;i++){
			Thread.sleep(100);
			System.out.println("学生信息...");
		}
	}
	
	@RabbitListener(queues="yanqun.infos")
	public void invokeService(Student student){
		System.out.println(student);
	}
}
