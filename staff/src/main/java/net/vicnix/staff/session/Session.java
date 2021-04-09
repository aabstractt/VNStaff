package net.vicnix.staff.session;

import net.vicnix.staff.provider.MongoDBProvider;

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

    public abstract void sendMessage(String message);

    public abstract void setDefaultAttributes();

    public abstract void showPlayer(Session session);

    public abstract void hidePlayer(Session session);
}