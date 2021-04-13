package net.vicnix.staff;

import net.vicnix.staff.session.Session;

public abstract class ConsoleUtils {

    protected static ConsoleUtils instance;

    public static ConsoleUtils getInstance() {
        return instance;
    }

    public static void setInstance(ConsoleUtils consoleUtils) {
        instance = consoleUtils;
    }

    public abstract void sendMessage(String message);

    public abstract Session getSessionPlayer(String name);
}