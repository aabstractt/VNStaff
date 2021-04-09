package net.vicnix.staff.command;

import net.vicnix.staff.CommandManager;
import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpigotLocateTeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equals("vanish")) return false;

        Session session = null;

        if (sender instanceof Player) {
            session = SessionManager.getInstance().getSession(((Player) sender).getUniqueId());
        }

        CommandManager.getInstance().executeCommand(session, "ltp", args);

        return false;
    }
}