package net.vicnix.staff.command;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionStorage;

import java.util.List;

public class VanishCommand extends SpigotCommand implements Command {

    public VanishCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public void execute(Session session, String[] args) {
        SessionStorage sessionStorage = session.getSessionStorage();

        sessionStorage.setVanished(!sessionStorage.isVanished());

        session.updateDefaultAttributes();

        session.sendMessage("&2Ahora eres &b" + (sessionStorage.isVanished() ? "invisible" : "visible") + "&2 para los demas jugadores.");
    }
}