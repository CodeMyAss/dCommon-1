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
public class Command_serverpvp extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player) sender;
		if (!player.hasPermission("serverpvp.yes")) {
			noPermission();
			return true;
		}
		if(args.length == 0) {
			showUsage(cmd);
		} else {
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable")) {
					player.getWorld().setPVP(false);
					Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + " has disabled pvp on the server.");
				} else {
					if(args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enable")) {
						player.getWorld().setPVP(true);
						Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + " has enabled pvp on the server.");
					}
				}
			}
		}
		return true;
	}

}
