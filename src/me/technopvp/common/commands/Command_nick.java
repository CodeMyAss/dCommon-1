package me.technopvp.common.commands;

import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_nick extends CommonCommand {
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player) sender;

		if (!player.hasPermission("nick.yes")) {
			noPermission();	
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		if (args.length == 1) {
//			Player target = Bukkit.getPlayer(args[0]);
//			if (args[0].equalsIgnoreCase(target.getName())) {
//				player.sendMessage("You have chaned " + target.getName() + "'s nick name");
//				return true;
//			}else {
			
			String msg = "";
			for (String arg : args) {
				msg = msg + arg + " ";
			}

			msg = msg.substring(0, msg.length() - 1);
			msg = msg.replaceAll("&", "§");
			
			player.sendMessage(Gray + "Your nick name has been changed to " + Red + msg);
			player.setDisplayName(msg + ChatColor.RESET);
//		}
			return true;
		}
		return true;
	}
}
