package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.StringUtils;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.OWNER)
public class Command_warnserver extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (!sender.hasPermission("warn.yes")) {
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
		for (Player all : Bukkit.getOnlinePlayers()) {
		all.playSound(all.getLocation(), Sound.EXPLODE, 1, 2);
		}
		String blank_broadcast_message = StringUtils.translate(plugin.messages.getString("warn_broadcast_message"));
		blank_broadcast_message = blank_broadcast_message.replace("%message", msg);
		blank_broadcast_message = blank_broadcast_message.replace("%player", sender.getName());
		
		Bukkit.broadcastMessage(blank_broadcast_message);	
		return true;
	}

}