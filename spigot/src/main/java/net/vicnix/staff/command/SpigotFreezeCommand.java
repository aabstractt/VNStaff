package net.vicnix.staff.command;

import net.vicnix.staff.ConsoleUtils;
import net.vicnix.staff.session.Session;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpigotFreezeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Run this command in-game");

            return true;
        }

        if (!sender.hasPermission("vicnix.staff")) {
            sender.sendMessage(ChatColor.RED + "No tienes permisos para ejecutar este comando");

            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Debe introducir el nombre del usuario a congelar");

            return false;
        }

        Session target = ConsoleUtils.getInstance().getSessionPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(ChatColor.RED + args[0] + " no encontrado.");

            return true;
        }

        target.setFreezed(!target.isFreezed());

        if (!target.isFreezed()) {
            target.sendMessage("&8------------------------------");
            target.sendMessage("&a Fuiste descongelado por " + sender.getName());
            target.sendMessage("&8------------------------------");
        }

        return true;
    }
}