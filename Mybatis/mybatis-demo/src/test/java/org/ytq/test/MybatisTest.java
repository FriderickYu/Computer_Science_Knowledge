package org.ytq.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.ytq.mapper.BrandMapper;
import org.ytq.pojo.Brand;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
//    @Test
//    public void testSelectAll() throws IOException {
//        // 1. 加载Mybatis核心配置文件，获取SqlSessionFactory
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        // 2. 获取SqlSession对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
//        List<Brand> brands = brandMapper.selectAll();
//        System.out.println(brands);
//        sqlSession.close();
//    }
//
//    @Test
//    public void testSelectById() throws IOException {
//
//        // 接收参数
//        int id = 1;
//        // 1. 加载Mybatis核心配置文件，获取SqlSessionFactory
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        // 2. 获取SqlSession对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
//        Brand brand = brandMapper.selectById(id);
//        System.out.println(brand);
//        sqlSession.close();
//    }

//    @Test
//    public void testSelectByCondition() throws IOException {
//
//        // 接收参数
//        int status = 1;
//        String companyName = "%华为%";
//        String brandName = "%华为%";
//        // 1. 加载Mybatis核心配置文件，获取SqlSessionFactory
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        // 2. 获取SqlSession对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
//        List<Brand> brand = brandMapper.selectByCondition(status, companyName, brandName);
//        System.out.println(brand);
//        sqlSession.close();
//    }

    @Test
    public void testSelectByCondition() throws IOException {

        // 接收参数
        int status = 1;
        String companyName = "%华为%";
        String brandName = "%华为%";
        // 1. 加载Mybatis核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 2. 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Brand brand1 = new Brand();
        brand1.setStatus(status);
        brand1.setCompanyName(companyName);
        brand1.setBrandName(brandName);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brand = brandMapper.selectByCondition(brand1);
        System.out.println(brand);
        sqlSession.close();
    }

}
