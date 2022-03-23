package com.qfdth.client01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//开启eureka客户端  标记当前项目是一个eureka的客户端
@EnableEurekaClient
public class Client01Application {

	public static void main(String[] args) {
		SpringApplication.run(Client01Application.class, args);
	}

}
