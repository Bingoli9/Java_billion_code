package com.yanqun.demo.service;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RemindService
{
	//指定执行的时间为“每分钟的第5秒”
	@Scheduled(cron="5 * * * * ?")
	public void callClassMeeting(){
		System.out.println(new Date());
		System.out.println("需要被提醒的业务（如召开班会)");
	}
}