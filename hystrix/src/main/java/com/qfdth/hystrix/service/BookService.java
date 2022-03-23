package com.qfdth.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.qfdth.demo.service.Book;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @Author TH
 * @Date 2022/3/20 13:45
 * @Version jdk1.8
 */
@Service
public class BookService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * 调用 storage 中的接口
     * @param ids
     * @return
     */
    @HystrixCommand
    public List<Book> getBooksByIds(List<Integer> ids){
        Book[] books = restTemplate.getForObject("http://storage/books?ids={1}", Book[].class, StringUtils.join(ids, ","));
        return Arrays.asList(books);
    }

    @HystrixCollapser(batchMethod = "getBooksByIds",
            collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds",value = "200")})
    public Future<Book> getBooksById(Integer id){
        return null;
    }
}
