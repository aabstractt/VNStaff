package net.vicnix.staff.listener;

import net.vicnix.staff.Staff;
import net.vicnix.staff.provider.MongoDBProvider;
import net.vicnix.staff.session.SpigotSession;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.session.SessionStorage;

public class PlayerJoinListener implements Listener {

    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerJoinEvent(PlayerJoinEvent ev) {
        Player player = ev.getPlayer();

        Bukkit.getScheduler().runTaskAsynchronously(Staff.getInstance(), () -> {
            if (!player.isOnline()) return;

            SessionStorage sessionStorage = null;

            if (player.hasPermission("vicnix.staff")) {
                sessionStorage = MongoDBProvider.getInstance().loadSessionStorage(player.getName(), player.getUniqueId());
            }

            if (sessionStorage == null) {
                sessionStorage = new SessionStorage(player.getName(), player.getUniqueId());
            }

            SessionManager.getInstance().createSession(new SpigotSession(sessionStorage));
        });
    }
}