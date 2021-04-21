package net.vicnix.staff.utils.action;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SpigotSession;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Collections;
import java.util.List;

public class FreezeAction extends IAction {

    @Override
    public void execute(Session... targets) {
        Session session = targets[0];

        SpigotSession target = (SpigotSession) targets[1];

        target.setFreezed(!target.isFreezed());

        if (!target.isFreezed()) {
            target.sendMessage("&8------------------------------");
            target.sendMessage("&a Fuiste descongelado por " + target.whoFreezed());
            target.sendMessage("&8------------------------------");
        }

        target.setWhoFreezed(target.isFreezed() ? session.getSessionStorage().getName() : null);
    }

    public List<Class<?>> getEventsAllowed() {
        return Collections.singletonList(PlayerInteractEntityEvent.class);
    }
}