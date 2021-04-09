package net.vicnix.staff.command;

import net.vicnix.staff.session.Session;

public interface Command {

    void execute(Session session, String[] args);
}