package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.utility.StringUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.MOD)
public class Command_clearchat extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (!sender.hasPermission("cc.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			StringUtils.clearchat();
			Bukkit.broadcastMessage(ChatColor.RED + "Chat has been cleared by " + sender.getName() + ".");	
			return true;
			}
		if (args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("silent") || args[0].equalsIgnoreCase("noshow") || args[0].equalsIgnoreCase("0")) {
			StringUtils.clearchat();
			return true;
		}
		if (args[0].equalsIgnoreCase("hidename") || args[0].equalsIgnoreCase("noname") || args[0].equalsIgnoreCase("nn") || args[0].equalsIgnoreCase("hn") || args[0].equalsIgnoreCase("2")) {
			StringUtils.clearchat();
			Bukkit.broadcastMessage(ChatColor.RED + "The chat has been cleared.");
			return true;
		}
		if (args[0].equalsIgnoreCase("help")) {
			sender.sendMessage(ChatColor.GRAY + "===== " + ChatColor.RED + "CC Help" + ChatColor.GRAY + " =====");
			sender.sendMessage(ChatColor.BLUE + "/cc is a easy way to clear chat, you can clear global chat or\n clear only one perons chat.");
			sender.sendMessage(ChatColor.RED + "/" + ChatColor.GRAY + "cc" + ChatColor.RED + " - " + ChatColor.GRAY + "Clears Chat.");
			sender.sendMessage(ChatColor.RED + "/" + ChatColor.GRAY + "cc <s>" + ChatColor.RED + " - " + ChatColor.GRAY + "Clears the chat silently.");
			sender.sendMessage(ChatColor.RED + "/" + ChatColor.GRAY + "cc <hn>" + ChatColor.RED + " - " + ChatColor.GRAY + "Clears the chat but, dosen't show your name.");
			if (sender.hasPermission("cc.other")) {
			sender.sendMessage(ChatColor.RED + "/" + ChatColor.GRAY + "cc <player>" + ChatColor.RED + " - " + ChatColor.GRAY + "Clears the chat of the player specified.");
			sender.sendMessage(ChatColor.RED + "/" + ChatColor.GRAY + "cc <player, s>" + ChatColor.RED + " - " + ChatColor.GRAY + "Clears the chat of the player silently.");
			sender.sendMessage(ChatColor.RED + "/" + ChatColor.GRAY + "cc <player, hn>" + ChatColor.RED + " - " + ChatColor.GRAY + "Clears the chat of the player but, dosen't show your name.");
			sender.sendMessage(ChatColor.BLUE + "- Example: /cc tpvp silent");
			sender.sendMessage(ChatColor.BLUE + "- Example: /cc silent");
			return true;
		}
			return true;
		}
		@SuppressWarnings("deprecation")
		Player target = Bukkit.getServer().getPlayer(args[0]);
		if (sender.hasPermission("cc.other")) {
		if (target == null) {
			sender.sendMessage(ChatColor.RED + "Could not find player " + "'" + args[0] + "'");			
			return true;
		}
		if (target == sender) {
			StringUtils.clearchat();
			sender.sendMessage(ChatColor.RED + "You have cleared your own chat.");
			return true;
		}
		if (args.length == 1) {
			StringUtils.clearchat();
			target.sendMessage(ChatColor.RED + "Your chat has been cleared by " + sender.getName() + ".");
			sender.sendMessage(ChatColor.GRAY + "You have cleared " + ChatColor.RED + target.getName() + "'s" + ChatColor.GRAY + " chat.");
			return true;
		}
		if (args.length == 2) {
		if (args[1].equalsIgnoreCase("s") || args[1].equalsIgnoreCase("silent") || args[1].equalsIgnoreCase("noshow") || args[1].equalsIgnoreCase("1")) {
			StringUtils.clearchat();
			sender.sendMessage(ChatColor.GRAY + "You have cleared " + ChatColor.RED + target.getName() + "'s" + ChatColor.GRAY + " chat silently.");
			return true;
		}
			if (args[1].equalsIgnoreCase("hidename") || args[1].equalsIgnoreCase("noname") || args[1].equalsIgnoreCase("nn") || args[1].equalsIgnoreCase("hn") || args[1].equalsIgnoreCase("2")) {
				StringUtils.clearchat();
				sender.sendMessage(ChatColor.GRAY + "You have cleared " + ChatColor.RED + target.getName() + "'s" + ChatColor.GRAY + " chat without showing your name.");
				target.sendMessage(ChatColor.RED + "Your chat has been cleared.");
				return true;
			}
		}
		}else {
			sender.sendMessage(plugin.usage);
			sender.sendMessage(ChatColor.GRAY + "You do not have permission to clear others chat.");
			return true;
		}
		return true;
	}
}