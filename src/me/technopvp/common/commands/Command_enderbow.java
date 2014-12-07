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
public class Command_enderbow extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("enderbow.yes")) {
			noPermission();
			return true;
		}
		if (!Lists.enderbow.contains(player.getName())) {
			Lists.enderbow.add(player.getName());
			player.sendMessage(ChatColor.GRAY + "Enderbow has been enabled");
			return true;
		}
		if (Lists.enderbow.contains(player.getName())) {
			Lists.enderbow.remove(player.getName());
			player.sendMessage(ChatColor.GRAY + "Enderbow has been disabled");
			return true;
		}
		return true;		
}
}
