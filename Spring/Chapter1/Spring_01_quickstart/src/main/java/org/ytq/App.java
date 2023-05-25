package org.ytq;

import org.ytq.service.BookService;
import org.ytq.service.impl.BookServiceImpl;

public class App {
    public static void main(String[] args){
        BookService bookService = new BookServiceImpl();
        bookService.save();
    }
}
