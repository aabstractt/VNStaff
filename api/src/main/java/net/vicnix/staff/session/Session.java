package net.vicnix.staff.session;

import java.util.Map;

public abstract class Session {

    protected SessionStorage sessionStorage;

    public Session(SessionStorage sessionStorage) {
        this.sessionStorage =sessionStorage;
    }

    public SessionStorage getSessionStorage() {
        return this.sessionStorage;
    }

    public abstract void teleportTo(Session session);

    public abstract void sendMessage(String message);

    public void setDefaultAttributes() {
    }

    /*public void syncRedis() {
        RedisProvider.getInstance().saveSessionStorage(this.sessionStorage.getUniqueId(), new HashMap<>() {{
            this.put("name", sessionStorage.getName());
            this.put("vanished", String.valueOf(sessionStorage.isVanished()));
            this.put("canSeeStaff", String.valueOf(sessionStorage.canSeeStaff()));
            this.put("freezed", String.valueOf(isFreezed()));
            this.put("whoFreezed", whoFreezed());
        }});
    }*/
}