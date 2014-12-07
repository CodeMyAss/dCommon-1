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
@Permissions(Permission.ADMIN)
public class Command_creativedrop extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("cdrop.yes") || !player.hasPermission("creativedrop.yes")) {
			noPermission();
			return true;
		}
		if (!Lists.cdrop.contains(player.getName())) {
			Lists.cdrop.add(player.getName());
			player.sendMessage(ChatColor.GRAY + "Creative item dropping is now enabled");	
			return true;
		}
		if (Lists.cdrop.contains(player.getName())) {
			Lists.cdrop.remove(player.getName());
			player.sendMessage(ChatColor.GRAY + "Creative item dropping is now disabled");
			return true;
		}
		return true;
		
		
}
}
