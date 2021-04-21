package net.vicnix.staff.provider;

import net.vicnix.staff.ConsoleUtils;
import net.vicnix.staff.session.Session;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.UUID;

public class RedisProvider {

    private static final RedisProvider instance = new RedisProvider();

    private JedisPool jedisPool;

    public static RedisProvider getInstance() {
        return instance;
    }

    public void init() {
        this.jedisPool = new JedisPool();

        String key = "servers:" + ConsoleUtils.getInstance().getServerName();

        //jedis.sadd(key, "0bligado");

        new Thread(() -> {
            while (this.jedisPool != null) {
                Jedis jedis = this.jedisPool.getResource();

                for (String sessionName : jedis.smembers(key)) {
                    this.loadSessionStorage(sessionName);

                    jedis.srem(key, sessionName);
                }
            }
        }).start();
    }

    public void loadSessionStorage(String sessionName) {
        Session session = ConsoleUtils.getInstance().getSessionPlayer(sessionName);

        if (session == null) return;

        Jedis jedis = this.jedisPool.getResource();

        Map<String, String> data = jedis.hgetAll("session:" + session.getSessionStorage().getUniqueId());

        if (data.isEmpty()) return;
    }

    public void saveSessionStorage(UUID uuid, Map<String, String> data) {
        Jedis jedis = this.jedisPool.getResource();

        jedis.hmset("session:" + uuid.toString(), data);
    }
}