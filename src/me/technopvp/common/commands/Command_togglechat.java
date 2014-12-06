package me.technopvp.common.commands;

import me.technopvp.common.Lists;
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
@Permissions(Permission.ANYONE)
public class Command_togglechat extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	private String enablechat = Gray + "You have " + Red + "enabled" + Gray  + " chat, you can now see all messages.";
	private String disablechat = Gray + "You have " + Red + "disabled" + Gray + " chat, you will no longer see any chat messages, exempting staff";
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;

		if (args.length == 0) {
			if (!Lists.togglechat.contains(player.getName())) {
				Lists.togglechat.add(player.getName());
				msg(disablechat);
			}else {
				Lists.togglechat.remove(player.getName());
				msg(enablechat);
				return true;
			}
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("true")) {
				if (Lists.togglechat.contains(player.getName())) {
					invalidUsage();
					msg(Gray + "Your chat is alredy disabled.");
					return true;
				}
//				PlayerUtils.disableChat(player);
				player.sendMessage(disablechat);
				return true;
			}
			if (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("false")) {
				if (!Lists.togglechat.contains(player.getName())) {
					invalidUsage();
					msg(Gray + "Your chat is alredy enabled.");
					return true;
				}
//				PlayerUtils.enableChat(player);
				player.sendMessage(enablechat);
				return true;
			}
			if (args[0].equalsIgnoreCase("toggle")) {
				if (!Lists.togglechat.contains(player.getName())) {
					Lists.togglechat.add(player.getName());
					msg(disablechat);
				}else {
					Lists.togglechat.remove(player.getName());
					msg(enablechat);
					return true;
				}
				return true;
			}
			if (player.hasPermission("togglechat.other")) {
				@SuppressWarnings("deprecation")
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					Utils.targetOffline(player, args[0]);
					return true;
				}
				if (!Lists.togglechat.contains(target.getName())) {
					Lists.togglechat.add(target.getName());
					msg(Gray + "You have " + Red + "disabled" + Gray + " chat for player " + Red + target.getName() + Gray + ".");
					target.sendMessage(disablechat);
				}else {
					Lists.togglechat.remove(target.getName());
					msg(Gray + "You have " + Red + "enabled" + Gray + " chat for " + Red + target.getName() + Gray + ".");
					target.sendMessage(enablechat);
					return true;
				}
				return true;
			}else {
				noPermission();
				return true;
			}
		}
		return true;
}
}
