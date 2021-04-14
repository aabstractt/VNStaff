package net.vicnix.staff.provider;

import net.vicnix.staff.session.Session;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
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

        Jedis jedis = this.jedisPool.getResource();

        jedis.hmset("session:0bligado", new HashMap<>() {{
            this.put("0bligado", "a");
        }});

        System.out.println("Hola, obteniendo datos de sessions");

        System.out.println(jedis.hgetAll("session:0bligado"));
    }

    public void update() {
    }

    public void loadSessionStorage(Session session) {
        Jedis jedis = this.jedisPool.getResource();

        Map<String, String> data = jedis.hgetAll("session:" + session.getSessionStorage().getUniqueId());

        if (data.isEmpty()) return;

        session.updateStorage(data);
    }

    public void saveSessionStorage(UUID uuid, Map<String, String> data) {
        Jedis jedis = this.jedisPool.getResource();

        jedis.hmset("session:" + uuid.toString(), data);
    }
}