package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.Utils;
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
@Permissions(Permission.MOD)
public class Command_fakejoin extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		if (!player.hasPermission("fakejoin.yes") || !player.hasPermission("fjoin.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			if (Utils.isVip(player)) {
				Bukkit.broadcastMessage(Gold + "" + Gold + ChatColor.BOLD + player.getName() + " has joined the game");
		}else {
			Bukkit.broadcastMessage(ChatColor.WHITE + player.getName() + " has joined the game");	
			return true;
		}
		} 
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			str.append(args[i] + " ");
		}
		
	    if (args.length == 1) {
	    	Bukkit.broadcastMessage(White + str.toString() + "has joined the game");
	    	return true;
	    }
	    return true;
}
}
