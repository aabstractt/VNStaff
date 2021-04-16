package net.vicnix.staff.utils.action;

import net.vicnix.staff.session.Session;

public class FreezeAction implements IAction {

    @Override
    public void run(Session... targets) {
        Session session = targets[0];

        Session target = targets[1];
    }
}