package com.qfdth.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TH
 * @Date 2022/3/26 16:27
 * @Version jdk1.8
 */
@RestController
//@RefreshScope
public class HelloController {

    @Value("${name}")
    String hello;

    @GetMapping("/hello")
    public String hello() {
        return hello;
    }
}
