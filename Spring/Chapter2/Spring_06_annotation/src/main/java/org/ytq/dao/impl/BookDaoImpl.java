package org.ytq.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.ytq.dao.BookDao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component(value = "dao")
@Scope("singleton")
public class BookDaoImpl implements BookDao {
    @Value("${name}")
    private String name;
    @Override
    public void save() {
        System.out.println("book dao save ..." + name);
    }

    @PostConstruct
    public void init(){
        System.out.println("book dao init ...");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("book dao destroy ...");
    }
}
