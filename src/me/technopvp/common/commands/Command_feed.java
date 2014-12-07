package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.Utils;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_feed extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		if (!player.hasPermission("feed.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			player.setFoodLevel(20);
			player.sendMessage(Gray + "You have been feed.");
			return true;
		}
		@SuppressWarnings("deprecation")
		Player t = Bukkit.getPlayer(args[0]);
		if (t == player) {
			player.setFoodLevel(20);
			player.sendMessage(Gray + "You have been feed.");
			return true;
		}
		if (player.hasPermission("feed.yes") || player.hasPermission("feed.other")) {
			if (t == null) {
				Utils.targetOffline(player, args[0]);
				return true;
			}
		if (args.length == 1) {
			player.setFoodLevel(20);
			t.sendMessage(Gray + "You have been feed.");
			player.sendMessage(Gray + "You have feed " + Red + t.getName() + Gray + ".");
			return true;
		}
	}else{
		player.sendMessage(Gray + "You do not have permission to feed other players");
		return true;
	}
		return true;
	}
}
