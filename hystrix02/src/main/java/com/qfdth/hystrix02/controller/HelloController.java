package com.qfdth.hystrix02.controller;

import com.qfdth.demo.service.Book;
import com.qfdth.hystrix02.feign.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TH
 * @Date 2022/3/20 14:47
 * @Version jdk1.8
 */
@RestController
public class HelloController {

    @Autowired
    BookService bookService;

    @GetMapping("/hello")
    public Book getBookById(){
        return bookService.getBookById(99);
    }
}
