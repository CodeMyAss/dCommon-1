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
@Permissions(Permission.OWNER)
public class Command_nokick extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("nokick.yes")) {
			noPermission();
			return true;
		}
		if (!Lists.nokick.contains(player.getName())) {
			Lists.nokick.add(player.getName());
			player.sendMessage(ChatColor.GRAY + "You will no longer be able to be kicked");			
			return true;
		}
		if (Lists.nokick.contains(player.getName())) {
			Lists.nokick.remove(player.getName());
			player.sendMessage(ChatColor.GRAY + "You can now get kicked from the game");				
			return true;
		}
		return true;
		
		
}
}
