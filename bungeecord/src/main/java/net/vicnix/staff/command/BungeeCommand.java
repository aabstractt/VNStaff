package net.vicnix.staff.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.vicnix.staff.CommandManager;
import net.vicnix.staff.session.Session;
import net.vicnix.staff.session.SessionManager;

public class BungeeCommand extends Command {

    public BungeeCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("vicnix.staff")) {
            sender.sendMessage(new ComponentBuilder("No tienes permisos para ejecutar este comando").color(ChatColor.RED).create());

            return;
        }

        Session session = null;

        if (sender instanceof ProxiedPlayer) {
            session = SessionManager.getInstance().getSession(((ProxiedPlayer) sender).getUniqueId());
        }

        CommandManager.getInstance().executeCommand(session, this.getName(), args);
    }
}