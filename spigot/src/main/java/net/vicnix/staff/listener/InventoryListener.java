package net.vicnix.staff.listener;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    @EventHandler (priority = EventPriority.NORMAL)
    public void onInventoryClick(InventoryClickEvent ev) {
        if(!(ev.getWhoClicked() instanceof Player)) return;

        ItemStack itemStack = ev.getCurrentItem();

        if(itemStack == null) return;

        Player player = (Player) ev.getWhoClicked();

        Session session = SessionManager.getInstance().getSession(player.getUniqueId());

        if (session == null || !session.getSessionStorage().isStaff()) return;

        for (ItemStack item : ItemUtils.getStaffContents(session.getSessionStorage().isVanished()).values()) {
            if (!item.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())) continue;

            session.sendMessage("Action for " + item.getItemMeta().getDisplayName());
        }
    }
}