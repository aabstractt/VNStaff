package net.vicnix.staff.listener;

import net.vicnix.staff.Staff;
import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.utils.ItemUtils;
import net.vicnix.staff.utils.action.IAction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {

    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent ev) {
        Player player = ev.getPlayer();

        if (ev.getAction().equals(Action.LEFT_CLICK_AIR) || ev.getAction().equals(Action.LEFT_CLICK_BLOCK)) return;

        ItemStack itemStack = ev.getItem();

        if(itemStack == null) return;

        Session session = SessionManager.getInstance().getSession(player.getUniqueId());

        if (session == null || !session.getSessionStorage().isStaff()) return;

        for (IAction action : Staff.getInstance().getActions()) {
            if (!action.canExecute(player.getInventory().getHeldItemSlot(), ev)) continue;

            action.execute(session);
        }

        for (ItemStack item : ItemUtils.getStaffContents(session.getSessionStorage().isVanished()).values()) {
            if (!item.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())) continue;

            ev.setCancelled(true);
        }
    }

    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent ev){
        Player player = ev.getPlayer();

        Session session = SessionManager.getInstance().getSession(player.getUniqueId());

        if(!session.getSessionStorage().isStaff()) return;

        if(!(ev.getRightClicked() instanceof Player)) return;

        Session target = SessionManager.getInstance().getSession(ev.getRightClicked().getUniqueId());

        if(target == null) return;

        if(target.getSessionStorage().isStaff()) return;

        for (IAction action : Staff.getInstance().getActions()) {
            if (!action.canExecute(player.getInventory().getHeldItemSlot(), ev)) continue;

            action.execute(session, target);
        }
    }
}