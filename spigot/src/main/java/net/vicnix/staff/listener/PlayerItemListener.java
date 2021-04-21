package net.vicnix.staff.listener;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerItemListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerDropItemEvent(PlayerDropItemEvent ev) {
        Player player = ev.getPlayer();

        Session session = SessionManager.getInstance().getSession(player.getUniqueId());

        if (session == null || !session.getSessionStorage().isStaff()) return;

        ItemStack itemStack = ev.getItemDrop().getItemStack();

        for (ItemStack item : ItemUtils.getStaffContents(session.getSessionStorage().isVanished()).values()) {
            if (!item.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())) continue;

            ev.setCancelled(true);

            return;
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    private void onPlayerPickupItemEvent(PlayerPickupItemEvent ev) {
        Player player = ev.getPlayer();

        if (player == null) return;

        Session session = SessionManager.getInstance().getSession(player.getUniqueId());

        if (session == null || !session.getSessionStorage().isStaff()) return;

        if (session.getSessionStorage().isVanished()) ev.setCancelled(true);
    }
}