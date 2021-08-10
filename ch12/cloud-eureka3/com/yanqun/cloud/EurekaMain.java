package com.yanqun.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
//开发Eureka注册中心，接收微服务的注册
public class EurekaMain {
	public static void main(String[] args) {
		SpringApplication.run(EurekaMain.class, args);
	}
}
