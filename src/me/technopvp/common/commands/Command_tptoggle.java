package me.technopvp.common.commands;

import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.utility.player.User;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.ADMIN)
public class Command_tptoggle extends CommonCommand {

	String teleportToggleOn = Gray + "Teleportation has been toggled to " + Red + Bold + "ON";
	String teleportToggleOff = Gray + "Teleportation has been toggled to " + Red + Bold + "OFF";
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("tptoggle.yes") || !player.hasPermission("toggletp.yes") || !player.hasPermission("teleporttoggle.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			if (!User.getUserConfig(player.getName()).contains("ToggleTeleport")) {
				User.getUserConfig(player.getName()).set("ToggleTeleport", true);
				User.getUser(player.getName()).getPlayerConfig().savePlayerConfig();
				msg(teleportToggleOn);
			}else {
				User.getUserConfig(player.getName()).set("ToggleTeleport", null);
				User.getUser(player.getName()).getPlayerConfig().savePlayerConfig();
				msg(teleportToggleOff);
				return true;
			}
			return true;
		}
		if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("true")) {
				User.getUserConfig(player.getName()).set("ToggleTeleport", true);
				User.getUser(player.getName()).getPlayerConfig().savePlayerConfig();
				msg(teleportToggleOn);
				return true;
			}
			if (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("false")) {
				User.getUserConfig(player.getName()).set("ToggleTeleport", null);
				User.getUser(player.getName()).getPlayerConfig().savePlayerConfig();
				msg(teleportToggleOff);
				return true;
			}
			
			@SuppressWarnings("deprecation")
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				targetOffline(args[0]);
				return true;
			}
			
			if (!User.getUserConfig(target.getName()).contains("ToggleTeleport")) {
				User.getUserConfig(target.getName()).set("ToggleTeleport", true);
				player.sendMessage(Gray + "You have toggled teleportation for " + Red + target.getName() + Gray + " to " + Red + Bold + "ON");
				User.getUser(target.getName()).getPlayerConfig().savePlayerConfig();
			}else {
				User.getUserConfig(target.getName()).set("ToggleTeleport", null);
				target.sendMessage(teleportToggleOff);
				player.sendMessage(Gray + "You have toggled teleportation for " + Red + target.getName() + Gray + " to " + Red + Bold + "OFF");
				User.getUser(target.getName()).getPlayerConfig().savePlayerConfig();
				return true;
			}
			return true;
		}
		return true;
	}
}
