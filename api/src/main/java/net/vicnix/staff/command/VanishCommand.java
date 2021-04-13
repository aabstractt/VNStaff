package net.vicnix.staff.command;

import net.vicnix.staff.session.Session;

public class VanishCommand implements Command {

    @Override
    public void execute(Session session, String[] args) {
        session.sendMessage("&aHola");
    }
}