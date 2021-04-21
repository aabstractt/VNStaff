package net.vicnix.staff.listener;

import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.session.SpigotSession;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

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
}