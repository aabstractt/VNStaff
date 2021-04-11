package net.vicnix.staff.listener;

import net.vicnix.staff.Staff;
import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.session.SpigotSession;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private Staff plugin = Staff.getInstance();

    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerQuitEvent(PlayerQuitEvent ev) {
        Player p = ev.getPlayer();
        SpigotSession playerSession = (SpigotSession) SessionManager.getInstance().getSession(p.getUniqueId());
        if(playerSession.isFreezed()) Bukkit.getServer().broadcast(ChatColor.translateAlternateColorCodes('&',"&3["+plugin.getName()+"] &6 El jugador &4"+p.getName()+" &6 se desconectó en ss"), "vicnix.staff");
        SessionManager.getInstance().closeSession(ev.getPlayer().getUniqueId());
    }
}
