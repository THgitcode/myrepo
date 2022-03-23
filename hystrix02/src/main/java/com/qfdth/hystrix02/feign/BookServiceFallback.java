package com.qfdth.hystrix02.feign;

import com.qfdth.demo.service.Book;
import org.springframework.stereotype.Component;

/**
 * @Author TH
 * @Date 2022/3/20 14:43
 * @Version jdk1.8
 */
@Component
public class BookServiceFallback implements BookService {
    /**
     * 这就是服务降级的方法
     *
     * @param id
     * @return
     */
    @Override
    public Book getBookById(Integer id) {
        Book book = new Book();
        book.setId(id);
        book.setName("服务降级了");
        return book;
    }
}
