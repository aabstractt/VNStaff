package net.vicnix.staff.utils.action;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionStorage;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class VanishAction extends IAction {

    @Override
    public void execute(Session... targets) {
        Session target = targets[0];

        SessionStorage sessionStorage = target.getSessionStorage();

        sessionStorage.setVanished(!sessionStorage.isVanished());

        target.setDefaultAttributes();

        target.sendMessage("&2Ahora eres &b" + (sessionStorage.isVanished() ? "invisible" : "visible") + "&2 para los demas jugadores.");
    }

    @Override
    public List<Class<?>> getEventsAllowed() {
        return new ArrayList<>() {{
            this.add(PlayerInteractEvent.class);
        }};
    }
}