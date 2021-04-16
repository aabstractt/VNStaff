package net.vicnix.staff.command;

import net.vicnix.staff.ConsoleUtils;
import net.vicnix.staff.session.Session;

public class FreezeCommand implements Command {

    @Override
    public void execute(Session session, String[] args) {
        if (session == null) {
            ConsoleUtils.getInstance().sendMessage("Run this command in-game");

            return;
        }

        if (args.length == 0) {
            session.sendMessage("&cDebe introducir el nombre del usuario a congelar");

            return;
        }

        Session target = ConsoleUtils.getInstance().getSessionPlayer(args[0]);

        if (target == null) {
            session.sendMessage(String.format("&c%s no encontrado.", args[0]));

            return;
        }

        target.setFreezed(!target.isFreezed());

        if (!target.isFreezed()) {
            target.sendMessage("&8------------------------------");
            target.sendMessage("&a Fuiste descongelado por " + target.whoFreezed());
            target.sendMessage("&8------------------------------");
        }

        target.setWhoFreezed(target.isFreezed() ? session.getSessionStorage().getName() : null);
    }
}