package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.api.fancymessage.FancyMessage;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_worldedit extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("worldedit")) {
			WorldEditPlugin worldeditplugin = ((WorldEditPlugin)plugin.getServer().getPluginManager().getPlugin("WorldEdit"));
			Selection we = worldeditplugin.getSelection(player);
			if (!plugin.getServer().getPluginManager().isPluginEnabled("WorldEdit")) {
				invalidUsage();
				new FancyMessage(Gray + "You must install worldedit for this command to work. " + Gray + "<" + Gold + "Click" + Gray + ">")
				.tooltip(ChatColor.RED + "" + ChatColor.RED + ChatColor.BOLD + "Click here to install worldedit.")
				.link("http://dev.bukkit.org/bukkit-plugins/worldedit/")
				.send(player);
				return true;
			}
			if (!player.hasPermission("worldedit.yes")) {
				noPermission();
				return true;
			}
			if (we == null) {
				invalidUsage();
				player.sendMessage(Gray + "Please select a area first");
				return true;
			}
			if (args.length == 0) {
			player.sendMessage(Gray + "Height: " + Red + we.getHeight() + Gray + "\nLength: " + Red + we.getLength() + Gray + "\nWidth: " + Red + we.getWidth() + Gray + "\nArea: " + Red + we.getArea());
			return true;
			}
			if (args[0].equalsIgnoreCase("height") || args[0].equalsIgnoreCase("h") || args[0].equalsIgnoreCase("hgh")) {
				player.sendMessage(Gray + "Height: " + Red + we.getHeight());
				return true;
			}
			if (args[0].equalsIgnoreCase("length") || args[0].equalsIgnoreCase("l") || args[0].equalsIgnoreCase("count") || args[0].equalsIgnoreCase("ct")) {
				player.sendMessage(Gray + "Length: " + Red + we.getLength());
				return true;
			}
			if (args[0].equalsIgnoreCase("width") || args[0].equalsIgnoreCase("w") || args[0].equalsIgnoreCase("wth")) {
				player.sendMessage(Gray + "Width: " + Red + we.getWidth());
				return true;
			}
			if (args[0].equalsIgnoreCase("area") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("are")) {
				player.sendMessage(Gray + "Area: " + Red + we.getArea());
				return true;
			}
			if (args[0].equalsIgnoreCase("clear") || args[0].equalsIgnoreCase("ce") || args[0].equalsIgnoreCase("rw")) {
				player.sendMessage(Gray + "You have cleared you're wand");
				we.getRegionSelector().clear();
				return true;
			}
			}
		return true;
		
	}
}