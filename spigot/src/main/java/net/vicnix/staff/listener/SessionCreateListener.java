package net.vicnix.staff.listener;

import net.vicnix.staff.Staff;
import net.vicnix.staff.event.SessionCreateEvent;
import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.session.SpigotSession;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class SessionCreateListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onSessionCreateEvent(SessionCreateEvent ev) {
        SpigotSession session = (SpigotSession) ev.getSession();

        Bukkit.getScheduler().runTask(Staff.getInstance(), () -> {
            session.setDefaultAttributes();

            session.updateFlyingAttribute();

            for (Session s : SessionManager.getInstance().getSessions().values()) {
                if (s.getSessionStorage().isStaff() || !s.getSessionStorage().isVanished()) continue;

                session.hidePlayer(s);
            }
        });
    }
}