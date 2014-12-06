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
public class Command_instanttnt extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		if (!player.hasPermission("instanttnt.yes")) {
			player.sendMessage(plugin.donor);
			return true;
		}
		if (!Lists.instatnt.contains(player.getName())) {
			Lists.instatnt.add(player.getName());
			player.sendMessage(ChatColor.GRAY + "You can now ignite tnt by right clicking it!");
					
			return true;
		}
		if (Lists.instatnt.contains(player.getName())) {
			Lists.instatnt.remove(player.getName());
			player.sendMessage(ChatColor.GRAY + "Instant tnt is now " + Red + "disabled" + Gray + ".");
			return true;
		}
		return true;
}
}
