package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.StringUtils;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Source(SourceType.ANY)
@Permissions(Permission.ADMIN)
public class Command_bc extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (!sender.hasPermission("bc.yes") || !sender.hasPermission("blankbroadcast.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		String msg = "";
		for (String arg : args) {
			msg = msg + arg + " ";
		}

		msg = msg.substring(0, msg.length() - 1);
		msg = msg.replaceAll("&", "§");

		String blank_broadcast_message = StringUtils.translate(plugin.messages.getString("blank_broadcast_message"));
		blank_broadcast_message = blank_broadcast_message.replace("%message", msg);
		blank_broadcast_message = blank_broadcast_message.replace("%player", sender.getName());

		Bukkit.broadcastMessage(blank_broadcast_message);
		return true;

	}
}
