package com.qfdth.hystrix02;

import org.springframework.boot.SpringApplication;

import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@SpringBootApplication
@SpringCloudApplication
@EnableFeignClients
public class Hystrix02Application {

    public static void main(String[] args) {
        SpringApplication.run(Hystrix02Application.class, args);
    }

}
