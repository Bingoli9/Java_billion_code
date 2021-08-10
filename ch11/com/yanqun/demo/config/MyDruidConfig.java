package com.yanqun.demo.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class MyDruidConfig {
	@ConfigurationProperties(prefix="spring.datasource")
	@Bean
	public DataSource druid(){
		return new DruidDataSource();
	}
}
