package com.qfdth.sentinel.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @Author TH
 * @Date 2022/3/27 12:21
 * @Version jdk1.8
 */
@RestController
public class HelloController {
    private static final Logger logger= LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String hello(){
        logger.info("hello sentinel---");
       return "hello  sentinel!!!";
    }

    @GetMapping("/hello2")
    public void hello2() {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 10; i++) {
            String s = restTemplate.getForObject("http://localhost:8081/hello", String.class);
            System.out.println("s = " + s+i);
        }
    }
}
