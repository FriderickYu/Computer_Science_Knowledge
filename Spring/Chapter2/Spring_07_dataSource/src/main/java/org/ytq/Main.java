package org.ytq;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.ytq.config.SpringConfig;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource);
    }
}