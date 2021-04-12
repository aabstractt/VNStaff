package net.vicnix.staff.command;

import net.vicnix.staff.Staff;
import net.vicnix.staff.session.SessionManager;
import net.vicnix.staff.session.SpigotSession;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpigotFreezeCommand implements CommandExecutor {
    private Staff plugin = Staff.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3["+plugin.getName()+"] &4 La consola no puede usar este comando"));
            return true;
        }
        Player playerSender = (Player) sender;
        if(!playerSender.hasPermission("vicnix.staff")){
            playerSender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&3["+plugin.getName()+"] &4 No tiene permisos para ejecutar este comando"));
            return true;
        }
        if(args.length == 0){
            playerSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3["+plugin.getName()+"] &4 Debe introducir el nombre del usuario a freezear"));
            return false;
        }

        Player toFreezePlayer = Bukkit.getPlayer(args[0]);
        if(toFreezePlayer == null){
            playerSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3["+plugin.getName()+"] El jugador "+args[0]+" no se encuentra conectado actualmente"));
            return true;
        }

        SpigotSession playerSession = (SpigotSession) SessionManager.getInstance().getSession(toFreezePlayer.getUniqueId());
        if(!playerSession.isFreezed()){
            playerSession.setFreezed(true);
            playerSession.sendMessage("&8 "+"------------------------------");
            playerSession.sendMessage("&c            No te desconectes!");
            playerSession.sendMessage("&e Fuiste freezeado por "+playerSender.getDisplayName());
            playerSession.sendMessage("&e Admites uso de &4 hacks &e o prefieres &6 ss");
            playerSession.sendMessage("&8 "+"------------------------------");
        }else{
            playerSession.setFreezed(false);
            playerSession.sendMessage("&8 "+"------------------------------");
            playerSession.sendMessage("&a Fuiste unfreezeado por "+playerSender.getDisplayName());
            playerSession.sendMessage("&8 "+"------------------------------");
        }

        return true;
    }
}
