package com.qfdth.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//开启feign客户端
@EnableFeignClients
public class OpenfeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenfeignApplication.class, args);
	}

}
