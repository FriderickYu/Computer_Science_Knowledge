package org.ytq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.ytq.dao.BookDao;
import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;

public class JDBCConfig {
    // 1. 定义一个方法获得要管理的对象
    @Value("com.mysql.jdbc.Driver")
    private String driver;
    @Value("jdbc:mysql://localhost:3306/spring_db")
    private String url;
    @Value("root")
    private String userName;
    @Value("root")
    private String password;
    // 2. 添加@Bean，表示当前方法的返回值是一个bean对象
    // @Bean修饰的方法，形参根据类型自动装配
    @Bean("dataSource")
    public DataSource dataSource(BookDao bookDao){
        System.out.println(bookDao);
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        return ds;
    }
}
