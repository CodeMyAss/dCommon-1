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
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_fly extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
	Player p = (Player)sender;
		

			if (!p.hasPermission("fly.yes")) {
				PlayerUtils.permission(p);
				return true;
			}
			if (args.length == 0) {
				if (p.getAllowFlight() == false) {
					p.setAllowFlight(true);
					p.setFlying(true);
					p.sendMessage(ChatColor.GRAY + "Fly Mode " + ChatColor.RED + ChatColor.BOLD + "enabled" + ChatColor.GRAY + ".");
					return true;
				}else {
					p.setAllowFlight(false);
					p.setFlying(false);
					p.sendMessage(ChatColor.GRAY + "Fly Mode " + ChatColor.RED + ChatColor.BOLD + "disabled" + ChatColor.GRAY + ".");
				}
			}
			
			if (args.length == 1) {
				@SuppressWarnings("deprecation")
				Player t = Bukkit.getPlayer(args[0]);
				if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("enable")) {
					p.setAllowFlight(true);
					p.setFlying(true);
					p.sendMessage(ChatColor.GRAY + "Fly Mode " + ChatColor.RED + ChatColor.BOLD + "enabled" + ChatColor.GRAY + ".");
					return true;
				}
				if (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("false") || args[0].equalsIgnoreCase("disable")) {
					p.setAllowFlight(false);
					p.setFlying(false);
					p.sendMessage(ChatColor.GRAY + "Fly Mode " + ChatColor.RED + ChatColor.BOLD + "disabled" + ChatColor.GRAY + ".");
					return true;
				}
				if (t == null) {
					Utils.targetOffline(sender, args[0]);
	                return true;			
				}
				if (t == p) {
					if (p.getAllowFlight() == false) {
						p.setAllowFlight(true);
						p.setFlying(true);
						p.sendMessage(ChatColor.GRAY + "Fly Mode " + ChatColor.RED + ChatColor.BOLD + "enabled" + ChatColor.GRAY + ".");
						return true;
					}else {
						p.setAllowFlight(false);
						p.setFlying(false);
						p.sendMessage(ChatColor.GRAY + "Fly Mode " + ChatColor.RED + ChatColor.BOLD + "disabled" + ChatColor.GRAY + ".");
						return true;
					}
				}
				if (p.hasPermission("fly.other") || p.hasPermission("fly.yes")) {
			if (args.length == 2) {
				if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("enable")) {
					t.setAllowFlight(true);
					t.setFlying(true);
					p.sendMessage(ChatColor.GRAY + "Fly mode " + ChatColor.RED + ChatColor.BOLD + "enabled" + ChatColor.GRAY + " for " + ChatColor.RED + ChatColor.BOLD + t.getName() + ChatColor.GRAY + ".");
					t.sendMessage(ChatColor.GRAY + "Fly Mode " + ChatColor.RED + ChatColor.BOLD + "enabled" + ChatColor.GRAY + ".");
					return true;
				}
				if (args[1].equalsIgnoreCase("off") || args[1].equalsIgnoreCase("false") || args[1].equalsIgnoreCase("disable")) {
					t.setAllowFlight(false);
					t.setFlying(false);
					t.sendMessage(ChatColor.GRAY + "Fly Mode " + ChatColor.RED + ChatColor.BOLD + "disabled" + ChatColor.GRAY + ".");
					p.sendMessage(ChatColor.GRAY + "Fly mode " + ChatColor.RED + ChatColor.BOLD + "disabled" + ChatColor.GRAY + " for " + ChatColor.RED + ChatColor.BOLD + t.getName() + ChatColor.GRAY + ".");
				  
					return true;
				}   
			}       
		}
			}
	return true;
}
}