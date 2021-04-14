package net.vicnix.staff.provider;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;

public class RedisProvider {

    private static final RedisProvider instance = new RedisProvider();

    private JedisPool jedisPool;

    public static RedisProvider getInstance() {
        return instance;
    }

    public void init() {
        this.jedisPool = new JedisPool();

        Jedis jedis = this.jedisPool.getResource();

        jedis.hmset("session:0bligado", new HashMap<>() {{
            this.put("0bligado", "a");
        }});

        System.out.println("Hola, obteniendo datos de sessions");

        System.out.println(jedis.hgetAll("session:0bligado"));
    }
}