package org.ytq;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    private Jedis jedis;

    // @BeforeEach注解: 在每一个测试单元之前都要执行一次
    @BeforeEach
    void setUp(){
        // 1. 建立连接
//        jedis = new Jedis("127.0.0.1", 6379);
        jedis = JedisConnectionFactory.getJedis();
        // 2. 设置密码
//        jedis.auth("194466");
        // 3. 选择库
        jedis.select(1);
    }

    @Test
    void testString(){
        // 实验, 存入一条数据
        String result = jedis.set("name", "虎哥");
        System.out.println("result = " + result);
        // 获取数据
        String name = jedis.get("name");
        System.out.println("name = " + name);

    }

    @Test
    void testHash(){
        jedis.hset("user:3", "name", "jack");
        jedis.hset("user:4", "name", "rose");
    }


    @AfterEach
    void tearDown(){
        if(jedis != null){
            jedis.close();
        }
    }
}
