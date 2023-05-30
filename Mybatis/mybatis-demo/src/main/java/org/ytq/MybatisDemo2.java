package org.ytq;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.ytq.mapper.UserMapper;
import org.ytq.pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.List;

public class MybatisDemo2 {
    // Mybatis代理开发
    public static void main(String[] args) throws IOException {
        // 1. 加载Mybatis核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取sqlsession对象，用它执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.1 获取对应的userMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();
        System.out.println(users);
        sqlSession.close();
    }
}
