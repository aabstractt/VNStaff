package net.vicnix.staff.listener;

import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.session.SpigotSession;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDamageEvent(EntityDamageEvent ev) {
        Entity entity = ev.getEntity();

        if (!(entity instanceof Player)) return;

        SpigotSession session = (SpigotSession) SessionManager.getInstance().getSession(entity.getUniqueId());

        if (session == null) return;

        if (!session.isFreezed()) return;

        ev.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent ev) {
        Entity entity = ev.getEntity();

        if (!(entity instanceof Player)) return;

        SpigotSession session = (SpigotSession) SessionManager.getInstance().getSession(entity.getUniqueId());

        if (session == null) return;

        if (!session.isFreezed()) return;

        ev.setCancelled(true);
    }

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

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockPlaceEvent(BlockPlaceEvent ev) {
        SpigotSession session = (SpigotSession) SessionManager.getInstance().getSession(ev.getPlayer().getUniqueId());

        if (session == null) return;

        if (!session.isFreezed()) return;

        ev.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreakEvent(BlockBreakEvent ev) {
        SpigotSession session = (SpigotSession) SessionManager.getInstance().getSession(ev.getPlayer().getUniqueId());

        if (session == null) return;

        if (!session.isFreezed()) return;

        ev.setCancelled(true);
    }
}