package net.vicnix.staff.provider;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisProvider {

    private static final RedisProvider instance = new RedisProvider();

    private JedisPool jedisPool;

    public static RedisProvider getInstance() {
        return instance;
    }

    public void init() {
        this.jedisPool = new JedisPool("localhost");

        Jedis jedis = this.jedisPool.getResource();

        jedis.auth("xavier123");

        jedis.set("vanishedPlayers", "a");

        System.out.println(jedis.get("vanishedPlayers"));
    }

    public void close() {
        this.jedisPool.close();
    }
}