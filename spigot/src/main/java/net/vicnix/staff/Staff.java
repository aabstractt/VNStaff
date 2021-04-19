package net.vicnix.staff;

import net.vicnix.staff.command.SpigotCommand;
import net.vicnix.staff.command.SpigotRestartCommand;
import net.vicnix.staff.listener.FreezeListener;
import net.vicnix.staff.listener.InventoryListener;
import net.vicnix.staff.listener.PlayerJoinListener;
import net.vicnix.staff.listener.PlayerQuitListener;
import net.vicnix.staff.provider.MongoDBProvider;
import net.vicnix.staff.provider.RedisProvider;
import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.utils.action.ConfigurationAction;
import net.vicnix.staff.utils.action.FreezeAction;
import net.vicnix.staff.utils.action.IAction;
import net.vicnix.staff.utils.action.VanishAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Staff extends JavaPlugin {

    public static Staff instance;

    private final Map<String, IAction> actions = new HashMap<>() {{
        this.put("vanish", new VanishAction());
        this.put("freeze", new FreezeAction());
        this.put("configuration", new ConfigurationAction());
    }};

    public static Staff getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;

        ConsoleUtils.setInstance(new SpigotConsoleUtils());

        this.saveDefaultConfig();
        this.reloadConfig();

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9&lVNStaff is starting"));

        MongoDBProvider.getInstance().init(this.getConfig().getString("mongouri", null));

        if (this.getConfig().getBoolean("redis-enabled", false)) {
            RedisProvider.getInstance().init();
        }

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lVNStaff mongodb loaded"));

        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        this.getServer().getPluginManager().registerEvents(new FreezeListener(), this);
        this.getServer().getPluginManager().registerEvents(new InventoryListener(), this);

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lVNStaff listeners loaded"));

        this.getServer().getPluginCommand("vanish").setExecutor(new SpigotCommand("vanish", "vanish"));
        this.getServer().getPluginCommand("stp").setExecutor(new SpigotCommand("ltp", "ltp"));
        this.getServer().getPluginCommand("devrestart").setExecutor(new SpigotRestartCommand());
        this.getServer().getPluginCommand("freeze").setExecutor(new SpigotCommand("freeze", "freeze"));

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lVNStaff commands loaded"));

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, this::update, 100);
    }

    public Boolean canDevAccess() {
        return this.getConfig().getBoolean("dev-access", true);
    }

    public void executeAction(String actionName, Session... targets) {
        IAction action = this.actions.get(actionName);

        if (action == null) {
            return;
        }

        action.execute(targets);
    }

    public void update() {
        for (Session session : SessionManager.getInstance().getSessions().values()) {
            if (!session.isFreezed()) continue;

            session.sendMessage("&8 ------------------------------");

            session.sendMessage("&c            No te desconectes!");
            session.sendMessage("&e Fuiste freezeado por " + session.whoFreezed());
            session.sendMessage("&e Admites uso de &4 hacks &e o prefieres &6 ss");

            session.sendMessage("&8 ------------------------------");
        }
    }
}