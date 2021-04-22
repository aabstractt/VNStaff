package net.vicnix.staff.listener;

import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.session.SpigotSession;
import net.vicnix.staff.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    private void onBlockPlaceEvent(BlockPlaceEvent ev) {
        Player player = ev.getPlayer();

        if (player == null) return;

        SpigotSession session = (SpigotSession) SessionManager.getInstance().getSession(player.getUniqueId());

        if (session == null) return;

        if (session.isFreezed()) ev.setCancelled(true);

        if (!session.getSessionStorage().isStaff()) return;

        ItemStack block = ev.getItemInHand();

        if (!block.hasItemMeta()) return;

        for (ItemStack item : ItemUtils.getStaffContents(session.getSessionStorage().isVanished()).values()) {
            if (!item.getItemMeta().getDisplayName().equals(block.getItemMeta().getDisplayName())) continue;

            ev.setCancelled(true);

            player.getInventory().setItemInHand(block);

            return;
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreakEvent(BlockBreakEvent ev) {
        SpigotSession session = (SpigotSession) SessionManager.getInstance().getSession(ev.getPlayer().getUniqueId());

        if (session == null) return;

        if (!session.isFreezed())  ev.setCancelled(true);
    }
}