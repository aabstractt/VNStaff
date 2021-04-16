package net.vicnix.staff.utils.action;

import net.vicnix.staff.session.Session;

public interface IAction {

    void run(Session... targets);
}