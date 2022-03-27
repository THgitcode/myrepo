package com.qfdth.nacos.controllre;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 百泽
 * @公众号 Java架构栈
 */
@RestController
/*自动刷新*/
@RefreshScope
public class HelloController {

    @Value("${name}")
    String name;

    @GetMapping("/hello")
    public String hello() {
        return name;
    }

}
