package com.yanqun.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
@MapperScan(value="com.yanqun.demo.mapper")
@SpringBootApplication
//@EnableCaching
//@EnableRabbit
//@EnableAsync
//@EnableScheduling
@ImportResource(locations={"classpath:applicationContext.xml"})
public class SpringBootSsmApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSsmApplication.class, args);
	}
}
