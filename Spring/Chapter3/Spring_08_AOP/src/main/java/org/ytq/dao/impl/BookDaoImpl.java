package org.ytq.dao.impl;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.ytq.dao.BookDao;

@Repository
public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println(System.currentTimeMillis());
        System.out.println("book dao save ...");
    }

    @Override
    public void update() {
        System.out.println("book dao update ...");
    }
}
