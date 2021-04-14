package net.vicnix.staff.command;

import net.vicnix.staff.CommandManager;
import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpigotCommand implements CommandExecutor {

    private final String apiCommand;

    private final String spigotCommand;

    public SpigotCommand(String apiCommand, String spigotCommand) {
        this.apiCommand = apiCommand;

        this.spigotCommand = spigotCommand;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equals(this.spigotCommand)) return false;

        if (!sender.hasPermission("vicnix.staff")) {
            sender.sendMessage(ChatColor.RED + "No tienes permisos para ejecutar este comando");

            return true;
        }

        Session session = null;

        if (sender instanceof Player) {
            session = SessionManager.getInstance().getSession(((Player) sender).getUniqueId());
        }

        CommandManager.getInstance().executeCommand(session, this.apiCommand, args);

        return false;
    }
}