package org.ytq.dao.impl;

import org.springframework.stereotype.Component;
import org.ytq.dao.BookDao;

@Component
public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("book dao save ...");
    }
}
