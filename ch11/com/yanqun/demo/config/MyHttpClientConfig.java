package com.yanqun.demo.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "http")
@Configuration
public class MyHttpClientConfig {
	@Value("${http.maxTotal}")
	private Integer maxTotal;
	@Value("${http.defaultMaxPerRoute}")
	private Integer defaultMaxPerRoute;
	@Value("${http.connectTimeout}")
	private Integer connectTimeout;
	@Value("${http.connectionRequestTimeout}")
	private Integer connectionRequestTimeout;
	 @Value("${http.socketTimeout}")
	private Integer socketTimeout;
	// 实例化连接池
	@Bean(name = "httpClientConnectionManager")
	public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager httpClientConnectionManager 
= new PoolingHttpClientConnectionManager();
		System.out.println(httpClientConnectionManager+"---------------"+maxTotal);
		httpClientConnectionManager.setMaxTotal(maxTotal);
		httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
		return httpClientConnectionManager;
	}

	// 注入HttpClientBuilder对象
	@Bean(name = "httpClientBuilder")
	public HttpClientBuilder getHttpClientBuilder(
			@Qualifier("httpClientConnectionManager") 
PoolingHttpClientConnectionManager httpClientConnectionManager) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setConnectionManager(httpClientConnectionManager);
		return httpClientBuilder;
	}

	// 注入httpClient
	@Bean
	public CloseableHttpClient getCloseableHttpClient(
			@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
		return httpClientBuilder.build();
	}

	// 注入RequestConfig.Builder
	@Bean(name = "builder")
	public RequestConfig.Builder getBuilder() {
		RequestConfig.Builder builder = RequestConfig.custom();
		return builder.setConnectTimeout(connectTimeout)
.setConnectionRequestTimeout(connectionRequestTimeout)
				 .setSocketTimeout(socketTimeout);
	}

	// 注入RequestConfig
	@Bean
	public RequestConfig getRequestConfig(@Qualifier("builder") 
RequestConfig.Builder builder) {
		return builder.build();
	}
}