package net.vicnix.staff.utils.action;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionStorage;

public class VanishAction implements IAction {

    @Override
    public void run(Session... targets) {
        Session target = targets[0];
        SessionStorage sessionStorage = target.getSessionStorage();

        if (!sessionStorage.isStaff()) {
            sessionStorage.setStaff(true);
        }

        sessionStorage.setVanished(!sessionStorage.isVanished());

        target.setDefaultAttributes();

        target.sendMessage("&2Ahora eres &b" + (sessionStorage.isVanished() ? "invisible" : "visible") + "&2 para los demas jugadores.");
    }
}