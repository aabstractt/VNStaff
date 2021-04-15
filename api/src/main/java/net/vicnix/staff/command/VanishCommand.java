package net.vicnix.staff.command;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionStorage;

public class VanishCommand implements Command {

    @Override
    public void execute(Session session, String[] args) {
        SessionStorage sessionStorage = session.getSessionStorage();

        if (!sessionStorage.isStaff()) {
            sessionStorage.setStaff(true);
        }

        sessionStorage.setVanished(!sessionStorage.isVanished());

        session.setDefaultAttributes();

        session.sendMessage("&2Ahora eres &b" + (sessionStorage.isVanished() ? "invisible" : "visible") + "&2 para los demas jugadores.");
    }
}