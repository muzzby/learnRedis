package com.hg.demo.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author admin
 */
public class StringRedisTemplateTest {

    private static final long serialVersionUID = 13435452023423L;

    /**
     * 如果无法注入RedisTemplate，就使用@Resource试试,自带的字符串模板类，用于存储字符串
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 自带的对象模板类，用于存储对象
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        // 保存字符串
        stringRedisTemplate.opsForValue().set("username", "redis!!!");
        Logger logger = LoggerFactory.getLogger(RedisTest.class);
        String str = stringRedisTemplate.opsForValue().get("username");
        logger.warn(str);
    }

    @Test
    public void test1() {
        UserEntity user = new UserEntity();
        user.setUserName("hello");
        user.setPassWord("12345");
        redisTemplate.opsForValue().set("user_1", user);
        UserEntity user1 = (UserEntity)redisTemplate.opsForValue().get("user_1");
        System.out.println(user1);
    }
}
