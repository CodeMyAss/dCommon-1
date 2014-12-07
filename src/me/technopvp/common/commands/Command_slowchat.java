package me.technopvp.common.commands;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.managers.MessageManager;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Source(SourceType.PLAYER)
@Permissions(Permission.MOD)
public class Command_slowchat extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (!sender.hasPermission("slowchat.yes")) {
			noPermission();
			return true;
		}
			if (args.length == 0) {
				showUsage(cmd);
				return true;
			}
			if (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable")) {
				if (Lists.slowchat == true) {
				Lists.slowchat = false;
				MessageManager.broadcast(null, "&cThe chat is no longer slown, you may now speak &lfreely!");
			}else {
				invalidUsage();
				sender.sendMessage(Gray + "That chat is not currently slown.");
				return true;
			}
				return true;
			}
			if (args.length == 1) {
				try {
				long time = Integer.parseInt(args[0]);
				if (!(time > 0)) {
					invalidImput();
					msg(Gray + "Integer must be over 0");
					return true;
				}
				Lists.slowtime.put("slowchattime", time);
				Lists.slowchat = true;

				MessageManager.broadcast(null, "&c&lThe chat has been slowed!\n&cYou can now talk every " + Green + Bold + time + Red + " second" +(time > 1 ? "s." : "."));
				return true;
			} catch (NumberFormatException error) {
				invalidUsage();
				sender.sendMessage(Gray + "It must be a valid number.");
				return true;
			}
	}
			return true;
	}
}
