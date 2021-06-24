package com.hg.demo.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author muzz
 */
public class RedisTest {

    /**
     * logback日志工具
     */
    private final Logger logger = LoggerFactory.getLogger(RedisTest.class);


    /**
     * jedis直连
     */
    @Test
    public void jedisTest(){
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("key", String.valueOf(123321));
        System.out.println(jedis.get("key"));
    }

    /**
     * jedisPool
     */
    @Test
    public void jedisPoolTest(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(30);
        config.setMaxIdle(10);
        config.setMaxWaitMillis(10*1000);
        JedisPool jedisPool = new JedisPool(config,"localhost",6379);
        try (Jedis jedis = jedisPool.getResource()) {
            //从连接池获取jedis核心对象
            //设置数据
            jedis.set("name", "hs");
            //获得数据
            String str = jedis.get("name");
            logger.warn("RedisPool：" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jedisPool.close();
    }



    /**
     * jedisPoolConfig1
     */
    @Test
    public void jedisPoolUtilTest1(){

        JedisPool a = JedisPoolUtil.getJedisPoolInstance();
        JedisPool b = JedisPoolUtil.getJedisPoolInstance();
        System.out.println(a == b);
        // true JedisPool的对象是单例模式的
    }

    /**
     * jedisPoolConfig2
     */
    @Test
    public void jedisPoolUtilTest2() {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        // 获取Redis连接对象
        try (Jedis jedis = jedisPool.getResource()) {
            // 业务
            jedis.set("key1", "value111");
//            jedis.close();
            System.out.println(jedis.get("key1"));
        }
    }
}
