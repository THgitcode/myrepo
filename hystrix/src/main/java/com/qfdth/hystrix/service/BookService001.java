package com.qfdth.hystrix.service;

import com.qfdth.demo.service.Book;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @Author TH
 * @Date 2022/3/18 22:32
 * @Version jdk1.8
 */
@Service
public class BookService001 {
    @Autowired
    RestTemplate restTemplate;
    /**
     * 调用 storage 中的接口
     * @param ids
     * @return
     */
    public List<Book> getBooksByIds(List<Integer> ids){
        Book[] books = restTemplate.getForObject("http://storage/books?ids={1}", Book[].class, StringUtils.join(ids, ","));
        return Arrays.asList(books);
    }

}
