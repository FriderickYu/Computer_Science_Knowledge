package org.ytq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ytq.config.SpringConfig;
import org.ytq.dao.BookDao;
import org.ytq.service.BookService;

public class AppForAnnotation {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = (BookDao) ctx.getBean("dao");
        ctx.registerShutdownHook();
        System.out.println(bookDao);
        BookService bookService = ctx.getBean(BookService.class);
        bookService.save();
    }
}
