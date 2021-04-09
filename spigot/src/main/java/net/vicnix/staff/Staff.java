package net.vicnix.staff;

import net.vicnix.staff.command.SpigotLocateTeleportCommand;
import net.vicnix.staff.command.SpigotRestartCommand;
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

        this.saveConfig();
        this.getConfig().options().copyDefaults(true);

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9&lVNStaff is starting"));

        MongoDBProvider.getInstance().init(this.getConfig().getString("mongouri", null));

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lVNStaff mongodb loaded"));

        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lVNStaff listeners loaded"));

        this.getServer().getPluginCommand("vanish").setExecutor(new SpigotVanishCommand());
        this.getServer().getPluginCommand("stp").setExecutor(new SpigotLocateTeleportCommand());
        this.getServer().getPluginCommand("devrestart").setExecutor(new SpigotRestartCommand());

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lVNStaff commands loaded"));
    }

    public Boolean canDevAccess() {
        return this.getConfig().getBoolean("dev-access", true);
    }
}