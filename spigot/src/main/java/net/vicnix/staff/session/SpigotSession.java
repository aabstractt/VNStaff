package net.vicnix.staff.session;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.stream.Stream;

public class SpigotSession extends Session {

    private boolean freezed = false;
    private String freezedByName = null;

    public SpigotSession(SessionStorage sessionStorage) { super(sessionStorage); }

    public void setDefaultAttributes() {
        if (!this.sessionStorage.isVanished()) return;

        Bukkit.getOnlinePlayers().forEach(player -> player.hidePlayer(this.getInstance()));

        Stream<Session> sessionStream = SessionManager.getInstance().getSessions().values().stream();

        sessionStream.filter(session -> session.getSessionStorage().canSeeStaff() && !session.getSessionStorage().getUniqueId().equals(this.getSessionStorage().getUniqueId())).forEach(session -> session.showPlayer(this));

        if (!this.sessionStorage.canSeeStaff()) return;

        sessionStream.filter(session -> session.getSessionStorage().isVanished() && !session.getSessionStorage().getUniqueId().equals(this.getSessionStorage().getUniqueId())).forEach(this::showPlayer);
    }

    public Player getInstance() {
        return Bukkit.getPlayer(this.sessionStorage.getUniqueId());
    }

    @Override
    public void teleportTo(Session session) {
        Player instance = this.getInstance();

        if (instance == null) return;

        instance.teleport(((SpigotSession) session).getInstance());
    }

    @Override
    public Boolean isFreezed(){
        return this.freezed;
    }
    @Override
    public void setFreezed(Boolean state){ this.freezed = state; }
    @Override
    public void setFreezedBy(String name){ this.freezedByName = name; }
    @Override
    public String getFreezedBy(){ return this.freezedByName; }

    @Override
    public void sendMessage(String message) {
        this.getInstance().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void showPlayer(Session session) {
        this.getInstance().showPlayer(((SpigotSession) session).getInstance());
    }

    public void hidePlayer(Session session) {
        this.getInstance().hidePlayer(((SpigotSession) session).getInstance());
    }
}