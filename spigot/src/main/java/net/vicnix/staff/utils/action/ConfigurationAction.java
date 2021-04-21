package net.vicnix.staff.utils.action;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SpigotSession;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.Collections;
import java.util.List;

public class ConfigurationAction extends IAction {

    @Override
    public void execute(Session... targets) {
        SpigotSession target = (SpigotSession) targets[0];

        Inventory inventory = Bukkit.createInventory(null, 9, "Configuration menu");

        target.getInstance().openInventory(inventory);
    }

    @Override
    public List<Class<?>> getEventsAllowed() {
        return Collections.singletonList(PlayerInteractEvent.class);
    }
}