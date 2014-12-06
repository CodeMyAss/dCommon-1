package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.utility.Utils;
import me.technopvp.common.utility.player.PlayerUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_gm extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
	Player player = (Player)sender;
	
		if (!player.hasPermission("gm.yes")) {
			PlayerUtils.permission(player);
			return true;
		}
		if (args.length == 0) {
			player.sendMessage(ChatColor.RED + "Usage: /gm <gamemode> <player>");
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative")) {
				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage(ChatColor.GRAY + "Your gamemode has been set to " + ChatColor.RED + ChatColor.BOLD + player.getGameMode().toString().toLowerCase() + ChatColor.GRAY + ".");
				return true;
			}
			if (args[0].equalsIgnoreCase("0")|| args[0].equalsIgnoreCase("s")  || args[0].equalsIgnoreCase("survival")) {
				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage(ChatColor.GRAY + "Your gamemode has been set to " + ChatColor.RED + ChatColor.BOLD + player.getGameMode().toString().toLowerCase() + ChatColor.GRAY + ".");
				return true;
			}
			if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure")) {
				player.setGameMode(GameMode.ADVENTURE);
				player.sendMessage(ChatColor.GRAY + "Your gamemode has been set to " + ChatColor.RED + ChatColor.BOLD + player.getGameMode().toString().toLowerCase() + ChatColor.GRAY + ".");
				return true;
			}else {
				player.sendMessage(plugin.usage);
				player.sendMessage(ChatColor.GRAY + "Usage: /gm <gamemode> <player>");
				return true;
			}
		}
		if (args.length == 2) {
			if (player.hasPermission("gm.other") || player.hasPermission("gm.yes")) {
			@SuppressWarnings("deprecation")
			Player t = Bukkit.getPlayer(args[1]);
			if (t == null) {
				Utils.targetOffline(player, args[1]);
				return true;
			}
			if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative")) {
				t.setGameMode(GameMode.CREATIVE);
				player.sendMessage(ChatColor.GRAY + "You have set " + ChatColor.RED + t.getName() + "'s " + ChatColor.GRAY + "gamemode to " + ChatColor.RED + ChatColor.BOLD + t.getGameMode().toString().toLowerCase() + ChatColor.GRAY + ".");
				return true;
			}
			if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival")) {
				t.setGameMode(GameMode.SURVIVAL);
				player.sendMessage(ChatColor.GRAY + "You have set " + ChatColor.RED + t.getName() + "'s " + ChatColor.GRAY + "gamemode to " + ChatColor.RED + ChatColor.BOLD + t.getGameMode().toString().toLowerCase() + ChatColor.GRAY + ".");
				return true;
			}
			if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure")) {
				t.setGameMode(GameMode.ADVENTURE);
				player.sendMessage(ChatColor.GRAY + "You have set " + ChatColor.RED + t.getName() + "'s " + ChatColor.GRAY + "gamemode to " + ChatColor.RED + ChatColor.BOLD + t.getGameMode().toString().toLowerCase() + ChatColor.GRAY + ".");
				return true;
			}
			}
		}else {
			player.sendMessage(plugin.usage);
			noPermission();
			return true;
		}
	return true;
	
}
}