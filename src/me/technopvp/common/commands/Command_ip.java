package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Source(SourceType.ANY)
@Permissions(Permission.ANYONE)
public class Command_ip extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		String ServerIp = plugin.getConfig().getString("server-ip");
	    ServerIp = ServerIp.replaceAll("&", "§");
	    ServerIp = ServerIp.replaceAll("%player", sender.getName());
	    sender.sendMessage(ChatColor.GRAY + "You are connected to: " + ServerIp);
		return true;
}
}
