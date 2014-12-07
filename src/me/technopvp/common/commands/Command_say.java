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
@Permissions(Permission.OWNER)
public class Command_say extends CommonCommand {
	dCommon plugin = dCommon.instance;	
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (!sender.hasPermission("say.yes")) {
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
		
		String server_say_message = StringUtils.translate(plugin.messages.getString("server_say_message"));
		server_say_message = server_say_message.replace("%player", sender.getName());
		server_say_message = server_say_message.replace("%message", msg);
		
		Bukkit.broadcastMessage(server_say_message);
		return true;
	}
}
