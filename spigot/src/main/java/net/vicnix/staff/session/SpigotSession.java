package net.vicnix.staff.session;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.stream.Stream;

public class SpigotSession extends Session {

    private boolean freezed = false;
    private String whoFreezed = null;

    public SpigotSession(SessionStorage sessionStorage) { super(sessionStorage); }

    public void setDefaultAttributes() {
        /*if (!this.sessionStorage.isVanished()) return;

        Bukkit.getOnlinePlayers().forEach(player -> player.hidePlayer(this.getInstance()));

        Stream<Session> sessionStream = SessionManager.getInstance().getSessions().values().stream();

        sessionStream.filter(session -> session.getSessionStorage().canSeeStaff() && !session.getSessionStorage().getUniqueId().equals(this.getSessionStorage().getUniqueId())).forEach(session -> session.showPlayer(this));

        if (!this.sessionStorage.canSeeStaff()) return;

        sessionStream.filter(session -> session.getSessionStorage().isVanished() && !session.getSessionStorage().getUniqueId().equals(this.getSessionStorage().getUniqueId())).forEach(this::showPlayer);
    */
        ItemStack tpItem = new ItemStack(Material.COMPASS);
        ItemMeta tpMeta = tpItem.getItemMeta();
        tpMeta.setDisplayName(ChatColor.GREEN + "Tp a jugador");
        tpItem.setItemMeta(tpMeta);

        ItemStack vanishItem = new ItemStack(Material.INK_SACK, 1, (byte) 10);
        ItemMeta vanishMeta = vanishItem.getItemMeta();
        vanishMeta.setDisplayName(ChatColor.GREEN + "Vanish on");
        vanishItem.setItemMeta(vanishMeta);

        ItemStack configItem = new ItemStack(Material.IRON_FENCE);
        ItemMeta configMeta = configItem.getItemMeta();
        configMeta.setDisplayName(ChatColor.GRAY + "Configuration");
        configItem.setItemMeta(configMeta);

        Player instance = this.getInstance();
        Inventory instanceInventory = instance.getInventory();

        instanceInventory.clear();
        instanceInventory.setItem(0, tpItem);
        instanceInventory.setItem(1, vanishItem);
        instanceInventory.setItem(8, configItem);
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
    public void setFreezed(Boolean state){
        this.freezed = state;
    }

    @Override
    public void setWhoFreezed(String name){
        this.whoFreezed = name;
    }

    @Override
    public String whoFreezed(){
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