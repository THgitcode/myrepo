package com.qfdth.hystrix.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.qfdth.demo.service.Book;

import java.util.List;

/**
 * @Author TH
 * @Date 2022/3/18 22:47
 * @Version jdk1.8
 */
//批量处理  请求
public class BookBatchCommand extends HystrixCommand<List<Book>> {

    private List<Integer> ids;
    private BookService001 bookService;
    //构造方法
    public BookBatchCommand(List<Integer> ids, BookService001 bookService) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("batchCmd")).andCommandKey(HystrixCommandKey.Factory.asKey("batchKey")));
        this.ids = ids;
        this.bookService = bookService;
    }
    //发起请求
    @Override
    protected List<Book> run() throws Exception {
        return bookService.getBooksByIds(ids);
    }

    /**
     * 这是服务降级的方法，即 run 方法执行失败的时候，会自动触发该方法的执行
     * @return
     */
    @Override
    protected List<Book> getFallback() {
        return null;
    }
}
