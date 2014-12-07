package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.ANYONE)
public class Command_who extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		String players = null;
	      for (Player online : Bukkit.getServer().getOnlinePlayers()) {
	          if(players == null) {
	        	  players = online.getDisplayName();
	          } else {
		    	  players = players + ", " + online.getDisplayName();
	      } 
	      }
	      sender.sendMessage(ChatColor.RED + "ADMIN, " + ChatColor.DARK_PURPLE + "MOD, "  + ChatColor.BLUE + "EZ, " + ChatColor.AQUA + "GOD, " + ChatColor.GREEN + "VIP, " + ChatColor.DARK_AQUA + "MVP, " + ChatColor.GOLD + "PRO, " + ChatColor.DARK_GREEN + "Ultimate, " + ChatColor.DARK_BLUE + "Master");
	      sender.sendMessage("(" + Red + Bukkit.getOnlinePlayers().length + White +"/" + Red + Bukkit.getMaxPlayers() + White + ")" + " [" + players + "]");
	      return true;
}
}
