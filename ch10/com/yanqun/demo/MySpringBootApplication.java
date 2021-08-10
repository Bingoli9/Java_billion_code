package com.yanqun.demo;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
@ImportResource(locations={"classpath:applicationContext.xml"})
@SpringBootApplication
public class MySpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class, args);
	}
}
