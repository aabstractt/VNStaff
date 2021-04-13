package net.vicnix.staff.command;

import net.vicnix.staff.ConsoleUtils;
import net.vicnix.staff.session.Session;

public class LocateTeleportCommand implements Command {

    @Override
    public void execute(Session session, String[] args) {
        if (args.length <= 0) {
            session.sendMessage("&cUso: /ltp <player>");

            return;
        }

        Session target = ConsoleUtils.getInstance().getSessionPlayer(args[0]);

        if (target == null) {
            session.sendMessage("&cJugador no encontrado");

            return;
        }

        session.teleportTo(target);

        session.sendMessage("&aTeleporting to " + target.getSessionStorage().getName());
    }
}