package net.vicnix.staff;

import net.vicnix.staff.command.SpigotFreezeCommand;
import net.vicnix.staff.command.SpigotLocateTeleportCommand;
import net.vicnix.staff.command.SpigotVanishCommand;
import net.vicnix.staff.listener.FreezeListener;
import net.vicnix.staff.listener.PlayerJoinListener;
import net.vicnix.staff.listener.PlayerQuitListener;
import net.vicnix.staff.provider.MongoDBProvider;
<<<<<<< Updated upstream
import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import org.bukkit.Bukkit;
=======
import net.vicnix.staff.provider.RedisProvider;
>>>>>>> Stashed changes
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
        RedisProvider.getInstance().init();

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lVNStaff mongodb loaded"));

        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        this.getServer().getPluginManager().registerEvents(new FreezeListener(), this);

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lVNStaff listeners loaded"));

        this.getServer().getPluginCommand("vanish").setExecutor(new SpigotVanishCommand());
        this.getServer().getPluginCommand("stp").setExecutor(new SpigotLocateTeleportCommand());
<<<<<<< Updated upstream
        this.getServer().getPluginCommand("devrestart").setExecutor(new SpigotRestartCommand());
        this.getServer().getPluginCommand("freeze").setExecutor(new SpigotFreezeCommand());
=======
>>>>>>> Stashed changes

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lVNStaff commands loaded"));
        scheduler();
    }

    public void onDisable() {
        RedisProvider.getInstance().close();
    }

    public Boolean canDevAccess() {
        return this.getConfig().getBoolean("dev-access", true);
    }
    public void scheduler(){
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
            for(Session session : SessionManager.getInstance().getSessions().values()) {
                if (!session.isFreezed()) continue;
                session.sendMessage("&8 "+"------------------------------");
                session.sendMessage("&c            No te desconectes!");
                session.sendMessage("&e Fuiste freezeado por "+session.getFreezedBy());
                session.sendMessage("&e Admites uso de &4 hacks &e o prefieres &6 ss");
                session.sendMessage("&8 "+"------------------------------");
            }
        }, 20*5);
    }
}