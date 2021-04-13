package net.vicnix.staff.listener;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerQuitEvent(PlayerQuitEvent ev) {
        Player player = ev.getPlayer();

        Session session = SessionManager.getInstance().getSession(player.getUniqueId());

        if(session.isFreezed()) {
            Bukkit.getServer().broadcast(ChatColor.translateAlternateColorCodes('&',"&6El jugador &4" + player.getName() + "&6 se desconectó en ss"), "vicnix.staff");
        }

        SessionManager.getInstance().closeSession(player.getUniqueId());
    }
}