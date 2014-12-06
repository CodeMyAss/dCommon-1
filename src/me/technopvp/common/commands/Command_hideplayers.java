package me.technopvp.common.commands;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_hideplayers extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		 if (!player.hasPermission("hide.yes") || !player.hasPermission("hideplayers.yes")) {
			 noPermission();
			 return true;
		  }
		  if (!Lists.vanish.contains(player.getName())) {
	        Lists.vanish.add(player.getName());
	        for (Player all : Bukkit.getOnlinePlayers()) {
	          player.hidePlayer(all);
	        }
	        player.sendMessage(ChatColor.GRAY + "All players have now been hidden.");
	      }else {
		  if (Lists.vanish.contains(player.getName())) {
	        Lists.vanish.remove(player.getName());
	        for (Player pls : Bukkit.getOnlinePlayers()) {
	          player.showPlayer(pls);
	        }
	        player.sendMessage(ChatColor.GRAY + "You can now see all players.");
	      }
	  }
		return true;
		
		
}
}
