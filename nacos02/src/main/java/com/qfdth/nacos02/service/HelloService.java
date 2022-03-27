package com.qfdth.nacos02.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 百泽
 * @公众号 Java架构栈
 */
@FeignClient("nacos01")
public interface HelloService {
    @GetMapping("/hello")
    String hello();
}
