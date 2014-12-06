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
public class Command_speedmode extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("speedmode.yes")) {
			noPermission();
			return true;
		}
		if (!Lists.speedmode.contains(player.getName())) {
			Lists.speedmode.add(player.getName());
			player.sendMessage(ChatColor.GRAY + "Speed mode has been activated");
			return true;
		}
		if (Lists.speedmode.contains(player.getName())) {
			Lists.speedmode.remove(player.getName());
			player.sendMessage(ChatColor.GRAY + "Speed mode has been disabled");
			return true;
		}
		return true;
		
		
}
}
