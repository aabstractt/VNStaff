package net.vicnix.staff.listener;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;
import net.vicnix.staff.provider.MongoDBProvider;
import net.vicnix.staff.session.BungeeSession;
import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.session.SessionStorage;

public class PostLoginListener implements Listener {

    @EventHandler (priority = EventPriority.NORMAL)
    public void onPostLoginEvent(PostLoginEvent ev) {
        ProxiedPlayer player = ev.getPlayer();

        if (!player.hasPermission("vicnix.staff")) return;

        new Thread(() -> {
            if (!player.isConnected()) return;

            SessionStorage sessionStorage = MongoDBProvider.getInstance().loadSessionStorage(player.getName(), player.getUniqueId());

            if (sessionStorage == null) {
                sessionStorage = new SessionStorage(player.getName(), player.getUniqueId());
            }

            SessionManager.getInstance().createSession(new BungeeSession(sessionStorage));
        }).start();
    }
}