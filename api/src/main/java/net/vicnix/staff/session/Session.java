package net.vicnix.staff.session;

import net.vicnix.staff.provider.MongoDBProvider;
import net.vicnix.staff.provider.RedisProvider;

import java.util.HashMap;
import java.util.Map;

public abstract class Session {

    protected SessionStorage sessionStorage;

    public Session(SessionStorage sessionStorage) {
        this.sessionStorage =sessionStorage;
    }

    public SessionStorage getSessionStorage() {
        return this.sessionStorage;
    }

    public void refresh() {
        this.sessionStorage = MongoDBProvider.getInstance().loadSessionStorage(this.sessionStorage.getName(), this.sessionStorage.getUniqueId());
    }

    public abstract void teleportTo(Session session);

    public abstract void sendMessage(String message);

    public abstract void setDefaultAttributes();

    public abstract void showPlayer(Session session);

    public abstract void hidePlayer(Session session);

    public abstract Boolean isFreezed();

    public abstract void setFreezed(Boolean state);

    public abstract void setWhoFreezed(String name);

    public abstract String whoFreezed();

    public void updateStorage(Map<String, String> data) {
        this.sessionStorage.setVanished(Boolean.valueOf(data.get("vanished")));

        this.sessionStorage.setCanSeeStaff(Boolean.valueOf(data.get("canSeeStaff")));

        this.setFreezed(Boolean.valueOf(data.get("freezed")));
    }

    public void syncRedis() {
        RedisProvider.getInstance().saveSessionStorage(this.sessionStorage.getUniqueId(), new HashMap<>() {{
            this.put("name", sessionStorage.getName());
            this.put("vanished", String.valueOf(sessionStorage.isVanished()));
            this.put("canSeeStaff", String.valueOf(sessionStorage.canSeeStaff()));
            this.put("freezed", String.valueOf(isFreezed()));
            this.put("freezedBy", whoFreezed());
        }});
    }
}