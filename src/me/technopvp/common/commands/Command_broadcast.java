package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.utility.StringUtils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Source(SourceType.ANY)
@Permissions(Permission.HELPER)
public class Command_broadcast extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (!sender.hasPermission("broadcast.yes")) {
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

		String broadcast = StringUtils.translate(plugin.messages.getString("broadcast"));
		broadcast = broadcast.replace("%message", msg);
		broadcast = broadcast.replace("%player", sender.getName());

		Bukkit.broadcastMessage(broadcast);

		return true;
	}

}