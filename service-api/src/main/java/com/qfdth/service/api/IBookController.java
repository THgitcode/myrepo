package com.qfdth.service.api;

import com.qfdth.demo.service.Book;
import org.springframework.web.bind.annotation.*;

/**
 * @Author TH
 * @Date 2022/3/18 12:47
 * @Version jdk1.8
 */

public interface IBookController {
    /**
     * 使用 OpenFeign 实现远程接口调用时，参数一定一定记得加注解
     * @param id
     * @return
     */
    @GetMapping("/book/")
    @ResponseBody
    Book getBookById(@RequestParam("id") Integer id);

    @DeleteMapping("/book/{id}")
    @ResponseBody
    void deleteBookById(@PathVariable("id") Integer id);

    @PostMapping("/book/")
    @ResponseBody
    Book addBook(@RequestBody Book book);

    @PutMapping("/book/")
    @ResponseBody
    void updateBook(@RequestBody Book book);
}
