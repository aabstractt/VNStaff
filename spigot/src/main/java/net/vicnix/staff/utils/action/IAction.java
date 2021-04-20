package net.vicnix.staff.utils.action;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.utils.ItemUtils;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public abstract class IAction {

    public abstract void execute(Session... targets);

    public abstract List<Class<?>> getEventsAllowed();

    public Boolean canExecute(Integer slot, Event event) {
        Map<?, ?> contents = ItemUtils.getStaffContent(slot);

        if (contents == null) return false;

        if (!contents.get("action").equals(this.getClass().getSimpleName())) return false;

        for (Class<?> tClass : this.getEventsAllowed()) {
            if (!tClass.isAssignableFrom(event.getClass())) continue;

            return true;
        }

        return false;
    }
}