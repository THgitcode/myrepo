package com.qfdth.hystrix02.feign;

import com.qfdth.demo.service.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author TH
 * @Date 2022/3/20 14:41
 * @Version jdk1.8
 */
/**
 * fallback = BookServiceFallback.class 表示处理服务降级的类
 */
@FeignClient(value = "storage",fallback =BookServiceFallback.class)
public interface BookService {

    @GetMapping("/book/")
    Book getBookById(@RequestParam("id") Integer id);
}
