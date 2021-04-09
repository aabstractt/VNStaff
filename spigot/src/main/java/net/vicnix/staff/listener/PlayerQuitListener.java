package net.vicnix.staff.listener;

import net.vicnix.staff.session.SessionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerQuitEvent(PlayerQuitEvent ev) {
        SessionManager.getInstance().closeSession(ev.getPlayer().getUniqueId());
    }
}
