package net.vicnix.staff;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;

public class BungeeConsoleUtils extends ConsoleUtils {

    @Override
    public void sendMessage(String message) {
        ProxyServer.getInstance().getLogger().info(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    public Session getSessionPlayer(String name) {
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(name);

        if (player == null) {
            return null;
        }

        return SessionManager.getInstance().getSession(player.getUniqueId());
    }

    @Override
    public void scheduleAsync(Runnable runnable) {
        ProxyServer.getInstance().getScheduler().runAsync(Staff.getInstance(), runnable);
    }
}