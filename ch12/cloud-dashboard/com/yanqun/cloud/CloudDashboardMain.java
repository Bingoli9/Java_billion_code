package com.yanqun.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class CloudDashboardMain {
	public static void main(String[] args) {
		SpringApplication.run(CloudDashboardMain.class, args);
	}
}
