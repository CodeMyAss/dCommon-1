package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.utility.StringUtils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Source(SourceType.ANY)
@Permissions(Permission.ADMIN)
public class Command_broadcastinfo extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (!sender.hasPermission("info.yes")) {
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
		
		String broadcast_info = StringUtils.translate(plugin.messages.getString("broadcast_info"));
		broadcast_info = broadcast_info.replace("%message", msg);
		broadcast_info = broadcast_info.replace("%player", sender.getName());
		
		Bukkit.broadcastMessage(broadcast_info);
		
		return true;
	}

}