package me.technopvp.common.commands;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_nopickup extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("nopickup.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
		if (!Lists.nopick.contains(player.getName())) {
			Lists.nopick.add(player.getName());
			player.sendMessage(ChatColor.GRAY + "You have turned no pick up " + Red + Bold + "ON" + Gray + ".");						
			return true;
		}
		if (Lists.nopick.contains(player.getName())) {
			Lists.nopick.remove(player.getName());
			player.sendMessage(ChatColor.GRAY + "You have turned no pick up " + Red + Bold + "OFF" + Gray + ".");					
			return true;
		}
	}
		return true;
		
		
}
}
