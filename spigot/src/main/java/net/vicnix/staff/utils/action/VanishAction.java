package net.vicnix.staff.utils.action;

import net.vicnix.staff.session.Session;

public class VanishAction implements IAction {

    @Override
    public void run(Session... targets) {
        Session target = targets[0];
    }
}