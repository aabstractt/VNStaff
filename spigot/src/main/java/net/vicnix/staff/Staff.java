package net.vicnix.staff;

import net.vicnix.staff.command.SpigotLocateTeleportCommand;
import net.vicnix.staff.command.SpigotVanishCommand;
import net.vicnix.staff.listener.PlayerJoinListener;
import net.vicnix.staff.listener.PlayerQuitListener;
import net.vicnix.staff.provider.MongoDBProvider;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Staff extends JavaPlugin {

    public static Staff instance;

    public static Staff getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9&lVNStaff is starting"));

        MongoDBProvider.getInstance().init(this.getConfig().getString("mongouri", null));

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lVNStaff mongodb loaded"));

        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lVNStaff listeners loaded"));

        this.getServer().getPluginCommand("vanish").setExecutor(new SpigotVanishCommand());
        this.getServer().getPluginCommand("ltp").setExecutor(new SpigotLocateTeleportCommand());
    }
}