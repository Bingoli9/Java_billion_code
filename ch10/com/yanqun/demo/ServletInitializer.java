package com.yanqun.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
public class ServletInitializer extends SpringBootServletInitializer {
	//当外置tomcat启动时，会自动触发configure()方法
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		//调用Spring Boot入口类
		return application.sources(SpringBootWebApplication.class);
	}
}
