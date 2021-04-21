package net.vicnix.staff.command;

import net.vicnix.staff.ConsoleUtils;
import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SpigotSession;

public class FreezeCommand extends SpigotCommand implements Command {

    public FreezeCommand(String name, String description, String usageMessage) {
        super(name, description, usageMessage);
    }

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

        SpigotSession target = (SpigotSession) ConsoleUtils.getInstance().getSessionPlayer(args[0]);

        if (target == null) {
            session.sendMessage(String.format("&c%s no encontrado.", args[0]));

            return;
        }

        target.setFreezed(!target.isFreezed());

        if (!target.isFreezed()) {
            target.sendMessage("&8&m ------------------------------");
            target.sendMessage("&a Fuiste descongelado por &c" + target.whoFreezed());
            target.sendMessage("&8&m ------------------------------");
        }

        target.setWhoFreezed(target.isFreezed() ? session.getSessionStorage().getName() : null);
    }
}