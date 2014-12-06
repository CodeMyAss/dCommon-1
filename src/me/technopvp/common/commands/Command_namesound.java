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
@Permissions(Permission.ANYONE)
public class Command_namesound extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("namesound.yes")) {
			noPermission();
			return true;
		}
		if (!Lists.namesound.contains(player.getName())) {
			Lists.namesound.add(player.getName());
			player.sendMessage(ChatColor.GRAY + "Name sound is now " + Red + "enabled" + Gray + ".");
			player.sendMessage(ChatColor.GRAY + "You will hear a sound when your name is said in chat.");
			return true;
		}
		if (Lists.namesound.contains(player.getName())) {
			Lists.namesound.remove(player.getName());
			player.sendMessage(ChatColor.GRAY + "Name sound is now " + Red + "disabled" + Gray + ".");
			return true;
		}
		return true;		
}
}
