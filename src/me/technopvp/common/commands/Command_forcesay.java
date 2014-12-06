package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_forcesay extends CommonCommand {
	dCommon plugin = dCommon.instance;	
	
	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
	
		if (!player.getName().equals("tpvp") || player.getName().equals("TechnoPvP")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		if (args.length == 1) {
			showUsage(cmd);
		} else if (args.length >= 2) {
			Player user = Bukkit.getServer().getPlayer(args[0]);
			if (user == null) {
				StringBuilder message = new StringBuilder(args[1]);
				for (int arg = 2; arg < args.length; arg++)
					message.append(" ").append(args[arg]);
				return true;
			}

			StringBuilder message = new StringBuilder(args[1]);
			for (int arg = 2; arg < args.length; arg++)
				message.append(" ").append(args[arg]);
			user.chat(message.toString());
		}
		return true;
	}
}
