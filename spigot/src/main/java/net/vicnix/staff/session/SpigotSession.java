package net.vicnix.staff.session;

import net.vicnix.staff.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SpigotSession extends Session {

    private boolean freezed = false;
    private String whoFreezed = null;

    private Boolean beforeIsFlying = false;

    public SpigotSession(SessionStorage sessionStorage) {
        super(sessionStorage);
    }

    @Override
    public void setDefaultAttributes() {
        this.setDefaultAttributes(false);
    }

    public void setDefaultAttributes(Boolean force) {
        if (this.sessionStorage.isStaff()) {
            ItemUtils.getStaffContents(this.sessionStorage.isVanished()).forEach((slot, itemStack) -> this.getInstance().getInventory().setItem(slot, itemStack));
        }

        if (this.sessionStorage.isVanished() && !force) {
            this.giveVanishAttributes();
        } else {
            this.removeVanishAttributes();
        }
    }

    public void updateFlyingAttribute() {
        Player instance = this.getInstance();

        if (!this.sessionStorage.isVanished() && !this.sessionStorage.isStaff()) {
            instance.setFlying(this.beforeIsFlying);

            instance.setAllowFlight(this.beforeIsFlying);

            return;
        }

        this.beforeIsFlying = instance.isFlying();

        instance.setAllowFlight(true);
        instance.setFlying(true);
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

    public void giveVanishAttributes() {
        if (!this.sessionStorage.isVanished()) return;

        Bukkit.getOnlinePlayers().forEach(player -> player.hidePlayer(this.getInstance()));

        for (Session session : SessionManager.getInstance().getSessions().values()) {
            if (!session.getSessionStorage().isStaff()) continue;

            if (session.getSessionStorage().canSeeStaff()) {
                ((SpigotSession) session).showPlayer(this);
            }

            if (!session.getSessionStorage().isVanished()) continue;

            if (!this.sessionStorage.canSeeStaff()) continue;

            this.showPlayer(session);
        }

        this.getInstance().spigot().setCollidesWithEntities(false);
    }

    public void removeVanishAttributes() {
        if (this.sessionStorage.isVanished()) return;

        Bukkit.getOnlinePlayers().forEach(player -> player.showPlayer(this.getInstance()));

        for (Session session : SessionManager.getInstance().getSessions().values()) {
            if (!session.getSessionStorage().isStaff()) continue;

            if (!session.getSessionStorage().isVanished()) continue;

            this.hidePlayer(session);
        }

        this.getInstance().spigot().setCollidesWithEntities(true);
    }

    @Override
    public void sendMessage(String message) {
        this.getInstance().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public Boolean isFreezed() {
        return this.freezed;
    }

    public void setFreezed(Boolean state) {
        this.freezed = state;
    }

    public void setWhoFreezed(String name) {
        this.whoFreezed = name;
    }

    public String whoFreezed() {
        return this.whoFreezed;
    }

    public void showPlayer(Session session) {
        this.getInstance().showPlayer(((SpigotSession) session).getInstance());
    }

    public void hidePlayer(Session session) {
        this.getInstance().hidePlayer(((SpigotSession) session).getInstance());
    }
}