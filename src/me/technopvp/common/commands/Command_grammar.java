package me.technopvp.common.commands;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_grammar extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("grammar.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			if (!Lists.forcedgrammar.contains(player.getName())) {
				Lists.forcedgrammar.add(player.getName());
				player.sendMessage(Gray + "You have " + Red + "enabled " + Gray + "forced grammar.");
				return true;
			}
			if (Lists.forcedgrammar.contains(player.getName())) {
				Lists.forcedgrammar.remove(player.getName());
				player.sendMessage(Gray + "You have " + Red + "disabled " + Gray + "forced grammar.");
				return true;
			}
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("true")) {
				Lists.forcedgrammar.add(player.getName());
				player.sendMessage(Gray + "You have " + Red + "enabled " + Gray + "forced grammar.");
				return true;
			}
			if (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("false")) {
				Lists.forcedgrammar.remove(player.getName());
				player.sendMessage(Gray + "You have " + Red + "disabled " + Gray + "forced grammar.");
				return true;
			}
		}
		return true;
		
		
}
}
