package org.ytq.service.impl;

import org.ytq.dao.BookDao;
import org.ytq.dao.impl.BookDaoImpl;
import org.ytq.service.BookService;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    @Override
    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }

    // 提供对应的set方法

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
