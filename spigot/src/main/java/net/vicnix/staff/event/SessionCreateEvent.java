package net.vicnix.staff.event;

import net.vicnix.staff.session.Session;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SessionCreateEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    private final Session session;

    public SessionCreateEvent(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return this.session;
    }
}
