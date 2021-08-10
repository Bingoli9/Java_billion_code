package com.yanqun.demo.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
//@EnableRedisHttpSession
public class MyConfig {

	// 自定义生成key策略：缓存key就是大写的方法名
	@Bean
	public KeyGenerator myKeyGenerator() {
		return (target, method, params) -> method.getName().toUpperCase();
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

@Bean
public HttpMessageConverters fastJsonHttpMessageConverters() {
	FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
	FastJsonConfig fastJsonConfig = new FastJsonConfig();
	//格式化
	fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
	fastConverter.setFastJsonConfig(fastJsonConfig);
	HttpMessageConverter<?> converter = fastConverter;
	return new HttpMessageConverters(converter);
}

	@Bean
	public JedisConnectionFactory connectionFactory() {
			RedisStandaloneConfiguration config	= new RedisStandaloneConfiguration("192.168.2.130",6379);
			JedisConnectionFactory connection = new JedisConnectionFactory(config);
			return connection;
	}
}
