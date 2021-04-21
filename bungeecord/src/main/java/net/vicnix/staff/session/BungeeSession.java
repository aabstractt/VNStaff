package net.vicnix.staff.session;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeSession extends Session {

    public BungeeSession(SessionStorage sessionStorage) {
        super(sessionStorage);
    }

    public ProxiedPlayer getInstance() {
        return ProxyServer.getInstance().getPlayer(this.sessionStorage.getUniqueId());
    }

    public Boolean isConnected() {
        return this.getInstance() != null;
    }

    @Override
    public void teleportTo(Session session) {
        if (!this.isConnected()) return;

        this.getInstance().connect(((BungeeSession) session).getInstance().getServer().getInfo());
    }

    @Override
    public void sendMessage(String message) {
        if (!this.isConnected()) return;

        this.getInstance().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}