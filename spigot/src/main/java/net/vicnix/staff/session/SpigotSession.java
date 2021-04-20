package net.vicnix.staff.session;

import net.vicnix.staff.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SpigotSession extends Session {

    private boolean freezed = false;
    private String whoFreezed = null;

    public SpigotSession(SessionStorage sessionStorage) {
        super(sessionStorage);
    }

    public void setDefaultAttributes() {
        if (!this.sessionStorage.isStaff()) return;

        ItemUtils.getStaffContents(this.sessionStorage.isVanished()).forEach((slot, itemStack) -> this.getInstance().getInventory().setItem(slot, itemStack));

        if (this.sessionStorage.isVanished()) {
            Bukkit.getOnlinePlayers().forEach(player -> player.hidePlayer(this.getInstance()));
        }

        for (Session session : SessionManager.getInstance().getSessions().values()) {
            if (!session.getSessionStorage().isStaff()) continue;

            if (session.getSessionStorage().canSeeStaff()) {
                session.showPlayer(this);
            }

            if (!session.getSessionStorage().isVanished()) continue;

            if (!this.sessionStorage.canSeeStaff()) continue;

            this.showPlayer(session);
        }
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
    public Boolean isFreezed() {
        return this.freezed;
    }

    @Override
    public void setFreezed(Boolean state) {
        this.freezed = state;
    }

    @Override
    public void setWhoFreezed(String name) {
        this.whoFreezed = name;
    }

    @Override
    public String whoFreezed() {
        return this.whoFreezed;
    }

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