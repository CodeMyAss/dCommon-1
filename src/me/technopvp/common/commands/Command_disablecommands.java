package me.technopvp.common.commands;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.managers.MessageManager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.OWNER)
public class Command_disablecommands extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;

		if (!player.hasPermission("disablecommands.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("toggle") || args[0].equalsIgnoreCase("turn")) {
				if (Lists.disabledcommands == false) {
					Lists.disabledcommands = true;
					Bukkit.broadcastMessage("" + Pink + Bold + "All server commands have been " + Red + Bold + "DISABLED" + Pink + Bold + ", no commands will be permitted at this time.");
					return true;
				}else {
					Lists.disabledcommands = false;
					MessageManager.broadcast(null, "" + Pink + Bold + "All commands have been " + Red + Bold + "ENABLED" + Pink + Bold + ", you may now use commands.");
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("true")) {
				Lists.disabledcommands = true;
				Bukkit.broadcastMessage("" + Pink + Bold + "All server commands have been " + Red + Bold + "DISABLED" + Pink + Bold + ", no commands will be permitted at this time.");
				return true;
			}
			if (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("false")) {
				Lists.disabledcommands = false;
				MessageManager.broadcast(null, "" + Pink + Bold + "All commands have been " + Red + Bold + "ENABLED" + Pink + Bold + ", you may now use commands.");
				return true;
			}
			return true;

		}
		return true;
	}
}
