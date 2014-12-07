package me.technopvp.common.commands;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_pickupcheck extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("pickupcheck.yes")) {
			noPermission();
			return true;
		}
		if (!Lists.iname.contains(player.getName())) {
			Lists.iname.add(player.getName());
			player.sendMessage(ChatColor.GRAY + "Item name turned on");
			return true;
		}
		if (Lists.iname.contains(player.getName())) {
			Lists.iname.remove(player.getName());
			player.sendMessage(ChatColor.GRAY + "Item name turned off");
			return true;
		}
		return true;		
}
}
