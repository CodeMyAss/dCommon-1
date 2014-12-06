package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.utility.StringUtils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.ADMIN)
public class Command_tip extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (!sender.hasPermission("tip.yes")) {
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
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.playSound(all.getLocation(), Sound.CHICKEN_EGG_POP, 5, 5);
		}
		
		msg = msg.substring(0, msg.length() - 1);
		
		String broadcast_tip = StringUtils.format(plugin.messages.getString("broadcast_tip"));
		broadcast_tip = broadcast_tip.replace("%message", msg);
		broadcast_tip = broadcast_tip.replace("%player", sender.getName());
		
		Bukkit.broadcastMessage(StringUtils.format(broadcast_tip));	
		return true;
	}

}