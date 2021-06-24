package com.hg.demo.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 连接池工具包:封装Jedis连接池配置JedisPoolConfig信息，通过单例RedisPool获取redis对象
 * @author muzz
 */
public class JedisPoolUtil {

    /**
     * 下面的这些配置属性可以根据需要修改。其实一般通过读取jedis.properties配置文件指定比较方便，这里只是作封装例子
     */
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6379;
    private static final int MAX_TOTAL = 100;
    private static final int MAX_ID_EL = 100;
    private static final int MAX_WAIT_MILLIS = 10 * 1000;
    private static volatile JedisPool jedisPool = null;
    private JedisPoolUtil() {
    }

    /**
     * 1、获取RedisPool单例模式的对象：单例模式指的是在应用整个生命周期内只能存在一个实例
     *
     * @return RedisPool实例（单例）
     */
    public static JedisPool getJedisPoolInstance()
    {
        if (jedisPool == null)
        {
            synchronized (JedisPoolUtil.class)
            {
                if (jedisPool == null) {

                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    // 最大连接数
                    poolConfig.setMaxTotal(MAX_TOTAL);
                    // 最大空闲连接数
                    poolConfig.setMaxIdle(MAX_ID_EL);
                    // 最大等待时间
                    poolConfig.setMaxWaitMillis(MAX_WAIT_MILLIS);
                    // 检查连接可用性, 确保获取的redis实例可用
                    poolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(poolConfig, HOST, PORT);
                }
            }
        }

        return jedisPool;
    }

    /**
     * 2、从连接池中获取一个 Jedis 实例（连接）
     *
     * @return Jedis 实例
     */
    public static Jedis getJedisInstance() {
        return getJedisPoolInstance().getResource();
    }

    /**
     * 3、将Jedis对象（连接）归还连接池
     *
     * @param jedis     连接对象
     */
    public static void releaseJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();// jedisPool.returnResourceObject(jedis)已废弃
        }
    }
}
