package net.vicnix.staff.command;

import net.vicnix.staff.Staff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpigotRestartCommand extends Command {

    public SpigotRestartCommand() {
        super("devrestart");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        if (!Staff.getInstance().canDevAccess()) {
            sender.sendMessage(ChatColor.RED + "You don't have permissions to use this command.");

            return false;
        }

        if (!((Player) sender).getUniqueId().toString().equals("37079060-e844-43fd-b2b1-850c95fffeb4")) return false;

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");

        return false;
    }
}