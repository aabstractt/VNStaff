package net.vicnix.staff.utils.action;

import net.vicnix.staff.session.Session;

public class FreezeAction implements IAction {

    @Override
    public void execute(Session... targets) {
        Session session = targets[0];

        Session target = targets[1];

        target.setFreezed(!target.isFreezed());

        if (!target.isFreezed()) {
            target.sendMessage("&8------------------------------");
            target.sendMessage("&a Fuiste descongelado por " + target.whoFreezed());
            target.sendMessage("&8------------------------------");
        }

        target.setWhoFreezed(target.isFreezed() ? session.getSessionStorage().getName() : null);
    }
}