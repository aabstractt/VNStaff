package net.vicnix.staff.listener;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
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
    public void onHit(EntityDamageEvent ev) {
        Entity entity = ev.getEntity();

        if (!(entity instanceof Player)) return;

        Session session = SessionManager.getInstance().getSession(entity.getUniqueId());

        if (session == null) return;

        if (!session.isFreezed()) return;

        ev.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerAttack(EntityDamageByEntityEvent ev) {
        Entity entity = ev.getEntity();

        if (!(entity instanceof Player)) return;

        Session session = SessionManager.getInstance().getSession(entity.getUniqueId());

        if (session == null) return;

        if (!session.isFreezed()) return;

        ev.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onMove(PlayerMoveEvent ev) {
        Location to = ev.getTo();
        Location from = ev.getFrom();

        if (to.getBlockX() == from.getBlockX() && to.getBlockY() == from.getBlockY() && to.getBlockZ() == from.getBlockZ()) return;

        Session session = SessionManager.getInstance().getSession(ev.getPlayer().getUniqueId());

        if (session == null) return;

        if (!session.isFreezed()) return;

        ev.setTo(ev.getFrom());
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockPlace(BlockPlaceEvent ev) {
        Session session = SessionManager.getInstance().getSession(ev.getPlayer().getUniqueId());

        if (session == null) return;

        if (!session.isFreezed()) return;

        ev.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent ev) {
        Session session = SessionManager.getInstance().getSession(ev.getPlayer().getUniqueId());

        if (session == null) return;

        if (!session.isFreezed()) return;

        ev.setCancelled(true);
    }
}