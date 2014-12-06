package me.technopvp.common.commands;

import java.util.ArrayList;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.managers.MessageManager;
import me.technopvp.common.utility.StringUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_chatcolor extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;

		String[] colorsblocked ={
				"magic", "strikethrough", "underline", "bold", "black"
		};

		if (!player.hasPermission("chatcolor.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		if (args.length == 1) {
			if (!player.hasPermission("chatcolor.bypass")) {
			for (String blockedcolor : colorsblocked) {
			if (args[0].equalsIgnoreCase(blockedcolor)) {
				invalidUsage();
				sender.sendMessage(Gray + "You can not use the color " + Red + args[0] + Gray + ".");
				return true;
			}
			}
			}
			if (args[0].equalsIgnoreCase("pink")) {
				Lists.chatcolor.put(player.getName(), ChatColor.LIGHT_PURPLE);
				MessageManager.message(sender, Gray + "Your chat color has been set to " + Pink + Bold + "PINK" + Gray + ".");
				return true;
			}
			if (args[0].equalsIgnoreCase("purple")) {
				Lists.chatcolor.put(player.getName(), ChatColor.DARK_PURPLE);
				MessageManager.message(sender, Gray + "Your chat color has been set to " + Purple + Bold + "PURPLE" + Gray + ".");
				return true;
			}
			if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("white")) {
				Lists.chatcolor.remove(player.getName());
				msg(Gray + "You have" + Red + " reset " + Gray + "your chatcolor back to normal.");
				return true;
			}
			if (args[0].equalsIgnoreCase("listcolors") || args[0].equalsIgnoreCase("colors") || args[0].equalsIgnoreCase("list")) {
				  ArrayList<String> colors = new ArrayList<String>();
			        for (ChatColor colorList : (ChatColor[])ChatColor.class.getEnumConstants()) {
			          colors.add(colorList.name());
			        }
			        player.sendMessage(Red + "ChatColors: " + Gray + colors.toString().replace("_", ""));
				return true;
			}

			for (ChatColor color : ChatColor.class.getEnumConstants()) {
			if (args[0].equalsIgnoreCase(StringUtils.removeUnderscore(color.name()))) {
				if (Lists.chatcolor.containsKey(player.getName())) {
				if (Lists.chatcolor.get(player.getName()) == color) {
					invalidUsage();
					player.sendMessage(Gray + "You already have the color " + color + StringUtils.removeUnderscore(color.name()) + Gray + ".");
					return true;
				}
				}
				Lists.chatcolor.put(sender.getName(), color);
				MessageManager.message(sender, Gray + "Your chat color has been set to " + color + Bold + StringUtils.removeUnderscore(color.name()) + Gray + ".");
				return true;
			}
		}
		}
		return true;
}
}
