package net.vicnix.staff;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SpigotConsoleUtils extends ConsoleUtils {

    public static ConsoleUtils getInstance() {
        if (instance == null) {
            instance = new SpigotConsoleUtils();
        }

        return instance;
    }

    @Override
    public Session getSessionPlayer(String name) {
        Player player = Bukkit.getPlayer(name);

        if (player == null) {
            return null;
        }

        return SessionManager.getInstance().getSession(player.getUniqueId());
    }

    @Override
    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}