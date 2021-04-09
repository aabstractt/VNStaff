package net.vicnix.staff;

public abstract class ConsoleUtils {

    protected static ConsoleUtils instance;

    public static ConsoleUtils getInstance() {
        return instance;
    }

    public abstract void sendMessage(String message);
}