package net.vicnix.staff.utils.action;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SpigotSession;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class ConfigurationAction implements IAction {

    @Override
    public void execute(Session... targets) {
        SpigotSession target = (SpigotSession) targets[0];

        Inventory inventory = Bukkit.createInventory(null, 9, "Configuration menu");

        target.getInstance().openInventory(inventory);
    }
}