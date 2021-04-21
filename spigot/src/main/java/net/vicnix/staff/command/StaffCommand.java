package net.vicnix.staff.command;

import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionStorage;
import net.vicnix.staff.session.SpigotSession;

import java.util.List;

public class StaffCommand extends SpigotCommand implements Command {

    public StaffCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public void execute(Session session, String[] args) {
        SessionStorage sessionStorage = session.getSessionStorage();

        sessionStorage.setStaff(!sessionStorage.isStaff());

        ((SpigotSession) session).setDefaultAttributes(true);
        ((SpigotSession) session).updateFlyingAttribute();

        session.sendMessage("&2Te has " + (sessionStorage.isStaff() ? "&bactivado" : "&cdesactivado") + "&2 el staffmode.");
    }
}
