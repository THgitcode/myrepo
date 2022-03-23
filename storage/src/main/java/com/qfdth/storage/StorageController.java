package com.qfdth.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TH
 * @Date 2022/3/17 22:09
 * @Version jdk1.8
 */
@RestController
public class StorageController {
    //将来区分提供服务
    @Value("${server.port}")
    Integer port;

    /**
     * 商品扣库存
     * @return
     */
    @GetMapping("deduct")
    public String deduct(){
        System.out.println("port = " + port);
        return "hello storage:"+port;
    }
}
