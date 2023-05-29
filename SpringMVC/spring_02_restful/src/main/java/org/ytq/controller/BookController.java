package org.ytq.controller;

// 简化RESTful开发


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.ytq.domain.Book;
import org.ytq.domain.User;

// @RestController等同于@Controller+@ResponseBody
@RestController
@RequestMapping("/books")
public class BookController {
    @PostMapping
    public String save(@RequestBody Book book){
        System.out.println("book save ..." + book);
        return "{'module': 'book save'}";
    }

    // 传参
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        System.out.println("book delete ..." + id);
        return "{'module': 'book delete'}";
    }

    @PutMapping
    public String update(@RequestBody Book book){
        System.out.println("book update ..." + book);
        return "{'module': 'book update'}";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id){
        System.out.println("book getById ..." + id);
        return "{'module': 'book getById'}";
    }
}
