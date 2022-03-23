package com.qfdth.openfeign.controller;

import com.qfdth.demo.service.Book;
import com.qfdth.openfeign.feign.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TH
 * @Date 2022/3/18 12:18
 * @Version jdk1.8
 */
@RestController
public class HelloController {
    @Autowired
    BookService bookService;

    @GetMapping("/test01")
    public void test01() {
        Book b1 = bookService.getBookById(1);
        System.out.println("b1 = " + b1);
        Book addBook = bookService.addBook(b1);
        System.out.println("addBook = " + addBook);
        bookService.deleteBookById(99);
        bookService.updateBook(b1);
    }
}
