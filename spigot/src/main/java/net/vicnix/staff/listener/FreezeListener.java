package net.vicnix.staff.listener;

import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.session.SpigotSession;
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
    public void onHit(EntityDamageEvent e){
        if ( e.getEntity() == null || !(e.getEntity() instanceof Player)) return;
        SpigotSession playerSession = (SpigotSession) SessionManager.getInstance().getSession(e.getEntity().getUniqueId());
        if(playerSession.isFreezed()) e.setCancelled(true);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerAttack(EntityDamageByEntityEvent e){
        if(e.getDamager() == null || !(e.getDamager() instanceof Player)) return;
        SpigotSession playerSession = (SpigotSession) SessionManager.getInstance().getSession(e.getDamager().getUniqueId());
        if(playerSession.isFreezed()) e.setCancelled(true);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onMove(PlayerMoveEvent e) {
        if (e.getTo().getBlockX() == e.getFrom().getBlockX() && e.getTo().getBlockY() == e.getFrom().getBlockY() && e.getTo().getBlockZ() == e.getFrom().getBlockZ())  return;
        SpigotSession playerSession = (SpigotSession) SessionManager.getInstance().getSession(e.getPlayer().getUniqueId());
        if(playerSession.isFreezed()) e.setTo(e.getFrom());
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockPlace(BlockPlaceEvent e) {
        SpigotSession playerSession = (SpigotSession) SessionManager.getInstance().getSession(e.getPlayer().getUniqueId());
        if(playerSession.isFreezed()) e.setCancelled(true);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent e) {
        SpigotSession playerSession = (SpigotSession) SessionManager.getInstance().getSession(e.getPlayer().getUniqueId());
        if(playerSession.isFreezed()) e.setCancelled(true);
    }
}
