package net.vicnix.staff.listener;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class InventoryListener implements Listener {
    public void onInventoryClick(InventoryClickEvent e) {
        if(!(e.getWhoClicked() instanceof Player)) return;

        Player player = (Player) e.getWhoClicked();
        Session session = SessionManager.getInstance().getSession(player.getUniqueId());

        //Todo Mirar si en la session el staff mode = true
        if(e.getCurrentItem() == null) return;

        String itemName = e.getCurrentItem().getItemMeta().getDisplayName();
        switch (itemName){
            case "Vanish on":
                //Todo llamar a función
                break;
            case "Tp a jugador":
                //Todo llamar a función
                break;
            case "Configuration":
                //Todo llamar a función
                break;
        }
    }
}
