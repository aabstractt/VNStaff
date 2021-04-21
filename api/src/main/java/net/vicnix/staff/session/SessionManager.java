package net.vicnix.staff.session;

import net.vicnix.staff.ConsoleUtils;
import net.vicnix.staff.provider.MongoDBProvider;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {

    private final static SessionManager instance = new SessionManager();

    private final ConcurrentHashMap<UUID, Session> sessionConcurrentHashMap = new ConcurrentHashMap<>();

    public static SessionManager getInstance() {
        return instance;
    }

    public void createSession(Session session) {
        this.sessionConcurrentHashMap.put(session.getSessionStorage().getUniqueId(), session);

        ConsoleUtils.getInstance().createSession(session);
    }

    public void closeSession(UUID uuid) {
        if (!this.sessionConcurrentHashMap.containsKey(uuid)) return;

        SessionStorage sessionStorage = this.sessionConcurrentHashMap.remove(uuid).getSessionStorage();

        new Thread(() -> MongoDBProvider.getInstance().saveSessionStorage(sessionStorage)).start();
    }

    public ConcurrentHashMap<UUID, Session> getSessions() {
        return this.sessionConcurrentHashMap;
    }

    public Session getSession(UUID uuid) {
        return this.sessionConcurrentHashMap.get(uuid);
    }
}