package org.ytq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ytq.dao.BookDao;
import org.ytq.service.BookService;

public class App2 {
    public static void main(String[] args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao bookDao = (BookDao) applicationContext.getBean("bookDao");
        bookDao.save();
        BookService bookService = (BookService) applicationContext.getBean("bookService");
        bookService.save();
    }
}
