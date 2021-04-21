package net.vicnix.staff;

import net.vicnix.staff.command.*;
import net.vicnix.staff.listener.*;
import net.vicnix.staff.provider.MongoDBProvider;
import net.vicnix.staff.provider.RedisProvider;
import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.session.SpigotSession;
import net.vicnix.staff.utils.action.ConfigurationAction;
import net.vicnix.staff.utils.action.FreezeAction;
import net.vicnix.staff.utils.action.IAction;
import net.vicnix.staff.utils.action.VanishAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Staff extends JavaPlugin {

    public static Staff instance;

    private final List<IAction> actions = new ArrayList<>() {{
        this.add(new VanishAction());
        this.add(new FreezeAction());
        this.add(new ConfigurationAction());
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

        this.registerCommand(new VanishCommand("vanish", "Staff command", "/vanish", new ArrayList<>() {{
            this.add("v");
        }}));
        this.registerCommand(new StaffCommand("staff", "Staff command", "/staff", new ArrayList<>() {{
            this.add("mod");
        }}));
        this.registerCommand(new SpigotCommand("ltp", "Staff command", "/ltp"));
        this.registerCommand(new FreezeCommand("freeze", "Staff command", "/freeze <player>"));
        this.registerCommand(new SpigotRestartCommand());

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lVNStaff commands loaded"));

        this.getServer().getPluginManager().registerEvents(new SessionCreateListener(), this);
        this.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        this.getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerItemListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        this.getServer().getPluginManager().registerEvents(new BlockListener(), this);

        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lVNStaff listeners loaded"));

        Bukkit.getScheduler().runTaskTimer(this, this::update, 20, 100);
    }

    public Boolean canDevAccess() {
        return this.getConfig().getBoolean("dev-access", true);
    }

    public final List<IAction> getActions() {
        return this.actions;
    }

    public void update() {
        for (Session session : SessionManager.getInstance().getSessions().values()) {
            if (!((SpigotSession) session).isFreezed()) continue;

            session.sendMessage("&8&m ------------------------------");

            session.sendMessage("&c            No te desconectes!");
            session.sendMessage("&e Fuiste freezeado por &a" + ((SpigotSession) session).whoFreezed());
            session.sendMessage("&e Admites uso de &4hacks &eo prefieres &6ss");

            session.sendMessage("&8&m ------------------------------");
        }
    }

    private void registerCommand(Command command) {
        SimplePluginManager pluginManager = (SimplePluginManager) this.getServer().getPluginManager();

        try {
            Field field = SimplePluginManager.class.getDeclaredField("commandMap");

            field.setAccessible(true);

            CommandMap commandMap = (CommandMap) field.get(pluginManager);

            commandMap.register(command.getName(), command);

            if (!(command instanceof net.vicnix.staff.command.Command)) return;

            CommandManager.getInstance().register(command.getName(), (net.vicnix.staff.command.Command) command);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}