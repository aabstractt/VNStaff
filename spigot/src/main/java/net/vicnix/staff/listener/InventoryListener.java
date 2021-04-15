package net.vicnix.staff.listener;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.session.SpigotSession;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class InventoryListener implements Listener {
    public void onInventoryClick(InventoryClickEvent e) {
        if(!(e.getWhoClicked() instanceof Player)) return;

        Player player = (Player) e.getWhoClicked();
        Session session = SessionManager.getInstance().getSession(player.getUniqueId());

        //Todo Mirar si en la session el staff mode = true
        if(e.getCurrentItem() == null) return;

        String itemName = e.getCurrentItem().getItemMeta().getDisplayName();
        switch (itemName){
            case "Vanish off":
                ItemStack vanishItem = new ItemStack(Material.INK_SACK, 1, (byte) 10);
                ItemMeta vanishMeta = vanishItem.getItemMeta();
                vanishMeta.setDisplayName(ChatColor.GREEN + "Vanish off");
                vanishItem.setItemMeta(vanishMeta);
                e.getInventory().setItem(1, vanishItem);
                //Todo llamar a función
                break;
            case "Vanish on":
                vanishItem = new ItemStack(Material.INK_SACK, 1, (byte) 0);
                vanishMeta = vanishItem.getItemMeta();
                vanishMeta.setDisplayName(ChatColor.GREEN + "Vanish off");
                vanishItem.setItemMeta(vanishMeta);
                e.getInventory().setItem(1, vanishItem);
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

    public void setVanish(Session session){
        SpigotSession a = (SpigotSession) session;
    }
}
