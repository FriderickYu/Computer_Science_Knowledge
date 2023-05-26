package org.ytq.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ytq.dao.BookDao;

@Component("test")
public class BookDaoImplTest implements BookDao {

    @Override
    public void save() {
        System.out.println("it's just a test");
    }
}
