package org.ytq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.ytq.dao.BookDao;
import org.ytq.service.BookService;

@Component("service")

public class BookServiceImpl implements BookService {
    @Autowired
    @Qualifier("dao")
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }
}
