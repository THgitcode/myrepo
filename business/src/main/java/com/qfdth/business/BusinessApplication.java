package com.qfdth.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//开启eureka客户端  标记当前项目是一个eureka的客户端
@EnableEurekaClient
public class BusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessApplication.class, args);
	}

	@Bean
	//开启负载均衡功能  默认策略轮巡
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
