package com.yanqun.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class ConfigBean 
{ 
	//将Spring Cloud提供的远程调用组件，注册到IoC容器中
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	@Bean
	public IRule consumeRule(){
		//采用"随机访问"的负载均衡规则
		return new RandomRule();
	}
}