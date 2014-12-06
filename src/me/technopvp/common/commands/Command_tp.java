package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.utility.player.User;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_tp extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if(!player.hasPermission("tp.yes") || !player.hasPermission("teleport.yes") || !player.hasPermission("tele.yes")) {
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
			if (User.getUserConfig(targetPlayer.getName()).getBoolean("ToggleTeleport") == true) {
				player.sendMessage(Red + targetPlayer.getName() + Gray + " has teleportation disabled.");
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
				if (User.getUserConfig(one.getName()).getBoolean("ToggleTeleport") == true) {
					player.sendMessage(Red + one.getName() + Gray + " has teleportation disabled.");
					return true;
				}
				if (User.getUserConfig(two.getName()).getBoolean("ToggleTeleport") == true) {
					player.sendMessage(Red + two.getName() + Gray + " has teleportation disabled.");
					return true;
				}
				
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