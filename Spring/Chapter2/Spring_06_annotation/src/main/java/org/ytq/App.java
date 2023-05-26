//package org.ytq;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.ytq.dao.BookDao;
//import org.ytq.service.BookService;
//
//public class App {
//    public static void main(String[] args) {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        BookDao bookDao = (BookDao) ctx.getBean("dao");
//        BookService bookService = (BookService) ctx.getBean("service");
//        System.out.println(bookDao);
////        bookService.save();
//    }
//}
