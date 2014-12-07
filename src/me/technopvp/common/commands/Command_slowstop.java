package me.technopvp.common.commands;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.api.barapi.BarApi;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.OWNER)
public class Command_slowstop extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	private int time = 0;
	private int task;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player p = (Player)sender;
		if (!p.hasPermission("slowstop.yes")) {
			noPermission();
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		  if (args[0].equalsIgnoreCase("stop") || args[0].equalsIgnoreCase("cancel") || args[0].equalsIgnoreCase("finish")) {
			  if (Lists.slowstop == true) {
			  BarApi.setMessage("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Server stopping has been " + ChatColor.RED + ChatColor.BOLD + "canceled" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "!", 2);
			  Bukkit.getScheduler().cancelAllTasks();
			  Lists.slowstop = false;
		  }else {
			  p.sendMessage(plugin.usage);
			  p.sendMessage(ChatColor.GRAY + "You are not currently stopping the server.");
			  return true;
		  }
			  return true;
		  }
		  
		  try {
		
		if (Lists.slowstop == false) {
		for (Player all : Bukkit.getOnlinePlayers()) {
		all.playSound(all.getLocation(), Sound.AMBIENCE_THUNDER, 5, 5);
		all.sendMessage(ChatColor.LIGHT_PURPLE + "---------- Server Restarting ----------" );
		all.sendMessage(ChatColor.LIGHT_PURPLE + "Server restarting now, we will be back soon!");
		all.sendMessage(ChatColor.LIGHT_PURPLE + "---------- Server Restarting ----------");
		}
			}
		
		if (Lists.slowstop == true) {
			p.sendMessage(plugin.usage);
			p.sendMessage(ChatColor.GRAY + "You are already stopping the server.");
			return true;
		}
		  
			Integer sstime = Integer.parseInt(args[0]);
			
			if (sstime <= 0) {
				p.sendMessage(plugin.usage);
				p.sendMessage(ChatColor.GRAY + "Number must be above 0");
				return true;
			}
		
		  time = sstime + 1;
		  task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				time--;
				  Lists.slowstop = true;
				for (Player all : Bukkit.getOnlinePlayers()) {
				if (time >= 4) {
						all.playSound(all.getLocation(), Sound.CLICK, 1, 1);
						BarApi.setMessage(ChatColor.RED + "" + ChatColor.GOLD + ChatColor.BOLD + "Server restarting in " + time);
						all.sendMessage(ChatColor.GRAY + "---------------------------------");
						all.sendMessage(ChatColor.GRAY + "----- Restarting in " + ChatColor.RED + ChatColor.BOLD + time + ChatColor.GRAY + " -----");
						all.sendMessage(ChatColor.GRAY + "---------------------------------");
				}
				
				if (time < 0 || time <= -3) {
					all.playSound(all.getLocation(), Sound.CHICKEN_EGG_POP, 5, 5);
				}
				if (time == 3) {
					all.playSound(all.getLocation(), Sound.NOTE_PLING, 1, 1);
					all.sendMessage("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Server restarting in " + ChatColor.RED + ChatColor.BOLD + time);
					BarApi.setMessage("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Server restarting in " + ChatColor.RED + ChatColor.BOLD + time);
				}	
				if (time == 2) {
					//Second number is the sound pitch
					all.playSound(all.getLocation(), Sound.NOTE_PLING, 1, 1);
					all.sendMessage("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Server restarting in " + ChatColor.RED + ChatColor.BOLD + time);
					BarApi.setMessage("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Server restarting in " + ChatColor.RED + ChatColor.BOLD + time);
				}
				if (time == 1) {
					all.playSound(all.getLocation(), Sound.NOTE_PLING, 1, 10);
					all.sendMessage("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Server restarting in " + ChatColor.RED + ChatColor.BOLD + time);
					BarApi.setMessage("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Server restarting in " + ChatColor.RED + ChatColor.BOLD + time);
				}
				if (time == 0) {
					all.playSound(all.getLocation(), Sound.NOTE_PLING, 1, 10);
					all.sendMessage("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "SERVER RESTARTING " + ChatColor.RED + "NOW" + ChatColor.LIGHT_PURPLE + ", BE BACK SOON!");
					BarApi.setMessage("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "=== SERVER RESTARTING NOW ===", 3);
				}
				} 
				if (time == -3) {
					Bukkit.shutdown();
				}
				if (time <= -4) {
					Bukkit.getScheduler().cancelTask(task);
					Lists.slowstop = false;
				}
			}
		}, 0, 25);
		  } catch (NumberFormatException e) {
			  p.sendMessage(plugin.usage);
			  p.sendMessage(ChatColor.GRAY + "You must specify an integer.(Number)");
			  return true;
		  }
		return true;
	}
	}
	
	