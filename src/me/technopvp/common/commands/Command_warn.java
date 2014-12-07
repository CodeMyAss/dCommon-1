package me.technopvp.common.commands;

import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;
import me.technopvp.common.utilities.player.Ban;
import me.technopvp.common.utilities.player.User;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.HELPER)
public class Command_warn extends CommonCommand {

	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!sender.hasPermission("warn.yes")) {
			noPermission();
			return true;
		}
		
		if (args.length < 1) {
			showUsage(cmd);
			return true;
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("check")) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					targetOffline(args[1]);
					return true;
				}
				if (!User.getUserConfig(target.getName()).contains("Warn")) {
					invalidImput();
					player.sendMessage(Gray + "That player has no warning records.");
				}else{
					player.sendMessage(Gray + "**** " + Red + player.getName() + Gray + " ****");
					player.sendMessage(Gray + "Warning Level: " + Red + User.getWarnedLevel(target.getName()));
					player.sendMessage(Gray + "Reason: " + Red + User.getUserConfig(target.getName()).getString("Warn.Reason"));
					player.sendMessage(Gray + "By: " + Red + User.getUserConfig(target.getName()).getString("Warn.By"));
					return true;
				}
				return true;
			}
		if (args[0].equalsIgnoreCase("clear")) {
			if (player.hasPermission("warn.*")) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					targetOffline(args[1]);
					return true;
				}
				if (!User.getUserConfig(target.getName()).contains("Warn")) {
					invalidImput();
					player.sendMessage(Gray + "That player has no warning records.");
				}else{
					User.getUserConfig(target.getName()).set("Warn", null);
					player.sendMessage(Gray + "You have cleared the warn recored for " + Red + target.getName() + Gray + ".");
					User.getUser(target.getName()).getPlayerConfig().savePlayerConfig();
					return true;
				}
				return true;
				}
			}
			
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			targetOffline(args[0]);
			return true;
		}
		String reason = args[1];
			User.warn(target.getName(),
					(User.getWarnedLevel(target.getName()) < 5 ? User
							.getWarnedLevel(target.getName()) + 1 : User
							.getWarnedLevel(target.getName())), reason, sender.getName());
		
		if (User.getWarnedLevel(target.getName()) == 1) {
			target.sendMessage("" + Red + Bold + "You have been warned. Warning Level: " + User.getWarnedLevel(target.getName()) + "\n");
			target.sendMessage("\n§7Reason: " + ChatColor.RED + ChatColor.BOLD + reason 
	        		  + "\n§7Punishment: " + ChatColor.RED + ChatColor.BOLD + "Message Warning" 
	        		  + "\n§7Warned By: " + ChatColor.RED + ChatColor.BOLD + player.getName()
	        		  + "\n§7Appeal at: " + ChatColor.RED + ChatColor.BOLD + plugin.getConfig().getString("config.Website"));
			return true;
		}
		if (User.getWarnedLevel(target.getName()) == 2) {
			player.kickPlayer("§c§nYou have been warned. Warning Level: " + User.getWarnedLevel(target.getName()) + "\n" 
        		  + "\n§7Reason: " + ChatColor.RED + ChatColor.BOLD + reason 
        		  + "\n§7Punishment: " + ChatColor.RED + ChatColor.BOLD + "First Kick" 
        		  + "\n§7Warned By: " + ChatColor.RED + ChatColor.BOLD + player.getName()
        		  + "\n§7Appeal at: " + ChatColor.RED + ChatColor.BOLD + plugin.getConfig().getString("config.Website"));
			return true;
		}
		if (User.getWarnedLevel(target.getName()) == 3) {
			player.kickPlayer("§c§nYou have been warned. Warning Level: " + User.getWarnedLevel(target.getName()) + "\n"  
					+ Red + Bold + "\nThis is your last warning before you are banned."
        		  + "\n§7Reason: " + ChatColor.RED + ChatColor.BOLD + reason 
        		  + "\n§7Punishment: " + ChatColor.RED + ChatColor.BOLD + "Last Kick" 
        		  + "\n§7Warned By: " + ChatColor.RED + ChatColor.BOLD + player.getName()
        		  + "\n§7Appeal at: " + ChatColor.RED + ChatColor.BOLD + plugin.getConfig().getString("config.Website"));
			return true;
		}
		if (player.hasPermission("warn.*") || player.isOp()  || player.hasPermission("warn.level.4")) {
		if (User.getWarnedLevel(target.getName()) == 4) {
			player.kickPlayer("§c§nYou have been warned. Warning Level: " + User.getWarnedLevel(target.getName()) + "\n"  
        		  + "\n§7Reason: " + ChatColor.RED + ChatColor.BOLD + reason 
        		  + "\n§7Punishment: " + ChatColor.RED + ChatColor.BOLD + "Ban" 
        		  + "\n§7Warned By: " + ChatColor.RED + ChatColor.BOLD + player.getName()
        		  + "\n§7Appeal at: " + ChatColor.RED + ChatColor.BOLD + plugin.getConfig().getString("config.Website"));
			User.getUserConfig(target.getName()).set("Warn", null);
			User.getUser(target.getName()).getPlayerConfig().savePlayerConfig();
			new Ban(target.getName(), reason.toString(), player.getName());
			target.setBanned(true);
			return true;
		}
		}else {
			player.sendMessage(Gray + "You don't have permission to warn someone past level " + Red + "3");
			return true;
		}
		return true;
	}
		return true;
	}

}
