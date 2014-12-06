package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.utility.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.OWNER)
public class Command_sethearts extends CommonCommand {
	dCommon plugin = dCommon.instance;	
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("sethearts.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;	
		}
		@SuppressWarnings("deprecation")
		Player target = Bukkit.getPlayer(args[0]);
		if (args.length == 1) {
			try {
			Integer hearts = Integer.parseInt(args[0]);
			if (hearts <= 0) {
				player.sendMessage(plugin.usage);
				player.sendMessage(Gray + "Number must be above 0");
				return true;
			}
			player.setHealthScale(hearts * 2);
			player.setHealth(player.getMaxHealth());
			player.sendMessage(Gray + "You have set your hearts to " + Red + hearts + Gray + ".");
			return true;
		} catch (NumberFormatException e) {
			player.sendMessage(plugin.usage);
			player.sendMessage(Gray + "Invalid number format.");
		}
		}
		if (args.length == 2) {
			if (player.hasPermission("sethearts.other")) {
			if (target == null) {
				Utils.targetOffline(player, args[0]);
				return true;
			}
			try {
			Integer hearts = Integer.parseInt(args[1]);
			if (hearts <= 0) {
				player.sendMessage(plugin.usage);
				player.sendMessage(Gray + "Number must be above 0");
				return true;
			}
			if (target == player) {
				player.setHealthScale(hearts * 2);
				player.setHealth(player.getMaxHealth());
				player.sendMessage(Gray + "You have set your hearts to " + Red + hearts + Gray + ".");
			}
			target.setHealthScale(hearts * 2);
			player.sendMessage(Gray + "You have set " + Red + target.getName() + "'s" + Gray + " hearts to " + Red + hearts + Gray + ".");
			target.sendMessage(Gray + "Your hearts have been set to " + Red + hearts + Gray + ".");
			target.setHealth(target.getMaxHealth());
			return true;
		} catch (NumberFormatException e) {
			player.sendMessage(plugin.usage);
			player.sendMessage(Gray + "Invalid number format.");
			return true;
		}
		}
		if (args.length > 2) {
			showUsage(cmd);
			return true;
		}
		}
		return true;
	
	}

}
