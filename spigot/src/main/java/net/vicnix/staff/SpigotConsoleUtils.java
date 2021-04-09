package net.vicnix.staff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class SpigotConsoleUtils extends ConsoleUtils {

    public static ConsoleUtils getInstance() {
        if (instance == null) {
            instance = new SpigotConsoleUtils();
        }

        return instance;
    }

    @Override
    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}