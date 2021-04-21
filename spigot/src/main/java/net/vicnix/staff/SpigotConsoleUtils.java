package net.vicnix.staff;

import net.vicnix.staff.event.SessionCreateEvent;
import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SpigotConsoleUtils extends ConsoleUtils {

    @Override
    public void createSession(Session session) {
        Bukkit.getPluginManager().callEvent(new SessionCreateEvent(session));
    }

    @Override
    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
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
    public void scheduleAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(Staff.getInstance(), runnable);
    }

    @Override
    public String getServerName() {
        return null;
    }
}