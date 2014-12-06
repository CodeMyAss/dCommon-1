package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.utility.PluginUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

@Source(SourceType.PLAYER)
@Permissions(Permission.OWNER)
public class Command_dcommon extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		final PluginDescriptionFile pdf = plugin.getDescription();

		if (!player.hasPermission("dcommon.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		if (args[0].equalsIgnoreCase("help")) {
			player.sendMessage(Red + "--- " + Gray + pdf.getName() + Red + "---");
			player.sendMessage(Blue + "/" + Gray + pdf.getName() + "reload");
			player.sendMessage(Blue + "/" + Gray + pdf.getName() + "restart");
			player.sendMessage(Blue + "/" + Gray + pdf.getName() + "disable");
			player.sendMessage(Blue + "/" + Gray + pdf.getName() + "info");
			return true;
		}
		if (args[0].equalsIgnoreCase("rl") | args[0].equalsIgnoreCase("reload")) {
			plugin.reloadConfig();
			player.sendMessage(Gray + pdf.getName() + Red + " " + pdf.getVersion() + Gray + " has been reloaded");
			return true;
		}
		if (args[0].equalsIgnoreCase("stop") | args[0].equalsIgnoreCase("disable")) {
			PluginUtils.disable(dCommon.instance);
			player.sendMessage(Gray + pdf.getName() + Red + " has been disabled");
			return true;
		}
		if (args[0].equalsIgnoreCase("start") || args[0].equalsIgnoreCase("enable")) {
			PluginUtils.enable(dCommon.instance);
			player.sendMessage(Gray + pdf.getName() + Red + " has been enable");
			return true;
		}
		if (args[0].equalsIgnoreCase("info") | args[0].equalsIgnoreCase("information")) {
			player.sendMessage(Gray + "Name: " + Blue + pdf.getName());
			player.sendMessage(Gray + "Author: " + Blue + pdf.getAuthors());
			player.sendMessage(Gray + "Version: " + Blue + pdf.getVersion());
			return true;
	}

		return true;
}
}
