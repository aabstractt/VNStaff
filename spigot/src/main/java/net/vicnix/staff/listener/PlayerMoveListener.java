package net.vicnix.staff.listener;

import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.session.SpigotSession;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerMoveEvent(PlayerMoveEvent ev) {
        Location to = ev.getTo();
        Location from = ev.getFrom();

        if (to.getBlockX() == from.getBlockX() && to.getBlockY() == from.getBlockY() && to.getBlockZ() == from.getBlockZ())
            return;

        SpigotSession session = (SpigotSession) SessionManager.getInstance().getSession(ev.getPlayer().getUniqueId());

        if (session == null) return;

        if (!session.isFreezed()) return;

        ev.setTo(from);
    }
}
