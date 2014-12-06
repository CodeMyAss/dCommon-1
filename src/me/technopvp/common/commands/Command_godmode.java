package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.utility.player.User;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_godmode extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	private String enabled = Gray + "You have " + Red + "enabled" + Gray + " god mode.";
	private String disabled = Gray + "You have " + Red + "disabled" + Gray + " god mode.";
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("godmode.yes") || !player.hasPermission("god.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			if (!User.getUserConfig(player.getName()).contains("GodMode")) {
				User.getUserConfig(player.getName()).set("GodMode", true);
				User.getUser(player.getName()).getPlayerConfig().savePlayerConfig();
				msg(enabled);
			}else {
				User.getUserConfig(player.getName()).set("GodMode", null);
				User.getUser(player.getName()).getPlayerConfig().savePlayerConfig();
				msg(disabled);
				return true;
			}
			 return true;
		}
		if (args.length == 1)  {
			if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enable")) {
				User.getUserConfig(player.getName()).set("GodMode", true);
				User.getUser(player.getName()).getPlayerConfig().savePlayerConfig();
				msg(enabled);
				return true;
			}
			if (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable")) {
				User.getUserConfig(player.getName()).set("GodMode", null);
				User.getUser(player.getName()).getPlayerConfig().savePlayerConfig();
				msg(disabled);
				return true;
			}
			
			@SuppressWarnings("deprecation")
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				targetOffline(args[0]);
				return true;
			}
			if (target == player) {
				if (!User.getUserConfig(player.getName()).contains("GodMode")) {
					User.getUserConfig(player.getName()).set("GodMode", true);
					User.getUser(player.getName()).getPlayerConfig().savePlayerConfig();
					msg(enabled);
				}else {
					User.getUserConfig(player.getName()).set("GodMode", null);
					User.getUser(player.getName()).getPlayerConfig().savePlayerConfig();
					msg(disabled);
					return true;
				}
				return true;
			}
			if (!User.getUserConfig(target.getName()).contains("GodMode")) {
				User.getUserConfig(target.getName()).set("GodMode", true);
				User.getUser(player.getName()).getPlayerConfig().savePlayerConfig();
				msg("You have " + Red + "enabled" + Gray + " god mode for " + Red + target.getName());
				target.sendMessage(enabled);
			}else {
				User.getUserConfig(target.getName()).set("GodMode", null);
				User.getUser(player.getName()).getPlayerConfig().savePlayerConfig();
				msg("You have " + Red + "disabled" + Gray + " god mode for " + Red + target.getName());
				target.sendMessage(disabled);
				return true;
			}
			return true;
		}
		
		return true;
		
}
}
