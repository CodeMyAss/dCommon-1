package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_tablist extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("tablist.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		if (args[0].length() >= 16) {
			invalidUsage();
			player.sendMessage(ChatColor.GRAY + "You can only have 16 characters in your tablistname");		
			return true;
		}
		if (args.length == 1) { 
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			str.append(args[i] + " ");
		}
		String system = str.toString();
		system = system.replaceAll("&", "§");
		
		player.getPlayerListName();
		player.setPlayerListName(system);
		player.sendMessage(Gray + "You have set you tablist name to " + ChatColor.RED + system);					
		player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
		return true;
	}else {
		invalidUsage();
		player.sendMessage(Gray + "Incorrect usage try again");
	}
		return true;
		
		
}
}
