package net.vicnix.staff.provider;

public class RedisProvider {

    private static final RedisProvider instance = new RedisProvider();

    public static RedisProvider getInstance() {
        return instance;
    }

    public void init() {

    }
}
