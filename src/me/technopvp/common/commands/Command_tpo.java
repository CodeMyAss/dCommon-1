package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_tpo extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if(!player.hasPermission("tpo.yes") || !player.hasPermission("teleporto.yes") || !player.hasPermission("teleo.yes")) {
			noPermission();
			return true;
		}
		if(args.length == 0) {
			showUsage(cmd);
		} else if(args.length == 1) {
			
			@SuppressWarnings("deprecation")
			Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
			if (targetPlayer == null) {
				targetOffline(args[0]);
				return true;
			}
				player.sendMessage(ChatColor.GRAY + "You have teleported to " + ChatColor.RED + targetPlayer.getName());
				player.teleport(targetPlayer);
			
		} else if(args.length == 2) {
			@SuppressWarnings("deprecation")
			Player one = plugin.getServer().getPlayer(args[0]);
			@SuppressWarnings("deprecation")
			Player two = plugin.getServer().getPlayer(args[1]);
			if(one != null && two != null) {	
				one.teleport(two);
				one.sendMessage(ChatColor.GRAY + "Teleporting " + ChatColor.RED + one.getName() + ChatColor.GRAY + " To " + ChatColor.RED + two.getName());
			} else {		
				player.sendMessage(ChatColor.GRAY + "Could not find player's " + ChatColor.RED + "'" + args[0] + "'" + ChatColor.GRAY + " and " + ChatColor.RED + "'" + args[1] + "'");
				return true;
			}
			two.sendMessage(ChatColor.GRAY + "Teleporting " + ChatColor.RED + one.getName() + ChatColor.GRAY + " To " + ChatColor.RED + two.getName());
		} else if(args.length >= 3) {
			showUsage(cmd);
		}
		return true;	
	}
}