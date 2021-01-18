package com.atguigu;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

public class redis01 {
    public static void main(String[] args) {
        Jedis jedis = getJedis();

        Set<String> keys = jedis.keys("*");

        for (String key : keys) {
            System.out.println(key);
        }

        String set = jedis.set("k100", "v100");

        jedis.close();

    }


    private static JedisPool jedisPool=null;

    public static Jedis getJedis(){
        if (jedisPool == null){
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(100);
            jedisPoolConfig.setMinIdle(20);
            jedisPoolConfig.setMaxIdle(30);
            jedisPoolConfig.setBlockWhenExhausted(true);
            jedisPoolConfig.setMaxWaitMillis(5000);
            jedisPoolConfig.setTestOnBorrow(true);

            jedisPool= new JedisPool("hadoop102",6379);
        }

        Jedis jedis = jedisPool.getResource();
        return jedis;
    }
}
