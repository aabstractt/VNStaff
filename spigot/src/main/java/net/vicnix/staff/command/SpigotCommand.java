package net.vicnix.staff.command;

import net.vicnix.staff.CommandManager;
import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpigotCommand extends Command {

    public SpigotCommand(String name, String description, String usageMessage) {
        this(name, description, usageMessage, new ArrayList<>());
    }

    public SpigotCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission("vicnix.staff")) {
            sender.sendMessage(ChatColor.RED + "No tienes permisos para ejecutar este comando");

            return true;
        }

        Session session = null;

        if (sender instanceof Player) {
            session = SessionManager.getInstance().getSession(((Player) sender).getUniqueId());
        }

        CommandManager.getInstance().executeCommand(session, this.getName(), args);

        return false;
    }
}