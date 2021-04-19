package net.vicnix.staff.listener;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteractAtEntityListener implements Listener {

    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent ev){
        Player player = ev.getPlayer();

        Session session = SessionManager.getInstance().getSession(player.getUniqueId());

        //TODO: Check if the item in hand is equal to the freeze item in config if(player.getItemInHand())
        if(!session.getSessionStorage().isStaff()) return;

        if(!(ev.getRightClicked() instanceof Player)) return;

        Session target = SessionManager.getInstance().getSession(ev.getRightClicked().getUniqueId());

        if(target == null) return;

        if(target.getSessionStorage().isStaff()) return;
        //TODO: call the freeze action
    }
}