package com.qfdth.storage;

import com.qfdth.demo.service.Book;
import com.qfdth.service.api.IBookController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author TH
 * @Date 2022/3/18 10:11
 * @Version jdk1.8
 */
@Controller
public class BookController implements IBookController {

    /**
     * ids 是调用方法传来的参数，这个参数格式是自定义的
     *
     * 1,2,3,4,5
     * @param ids
     * @return
     */
    @GetMapping("/books")
    @ResponseBody
    public List<Book> getBooksByIds(String ids){
        //调用方传来的id  把它拆分为一个list集合
        List<Integer> list = Arrays.stream(ids.split(",")).map(id -> Integer.parseInt(id)).collect(Collectors.toList());
        List<Book> books = new ArrayList<>();
        for (Integer id : list) {
            Book b = new Book();
            b.setId(id);
            books.add(b);
        }
        System.out.println("ids = " + ids);
        return books;
    }

    @Override
    public Book getBookById(Integer id) {
        Book book = new Book();
        book.setId(id);
        System.out.println("id = " + id);
        return book;
    }

    @Override
    public void deleteBookById(@PathVariable Integer id) {
        System.out.println("id = " + id);
    }
    //openfeign 传对象必须是Json格式 对象
    @Override
    public Book addBook(@RequestBody Book book) {
        return book;
    }

    //重定向
    @PostMapping("/add")
    public String addBook2(@RequestBody Book book) {
        System.out.println("addBook2 = " + book);
        return "redirect:/index";
    }

    @Override
    public void updateBook(@RequestBody Book book) {
        System.out.println("book = " + book);
    }

}
