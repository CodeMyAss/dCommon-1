package me.technopvp.common.commands;

import java.util.List;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.utility.player.PlayerUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_clearmobs extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		
		Player p = (Player)sender;
			if (!p.hasPermission("clearmobs.yes")) {
				PlayerUtils.permission(p);
				return true;
			}
			if (args.length == 0) {
				showUsage(cmd);
			}
		if(args.length == 1){
			if (args[0].equalsIgnoreCase("all")) {
				 int mobs = 0;     
					List<Entity> nearbyy = p.getWorld().getEntities();			
					for(Entity tmp : nearbyy){				
				    	        if ((tmp instanceof LivingEntity)) {
				    	          if (!(tmp instanceof Player)) {    	    
							mobs++;
							tmp.remove();
				    	          }
					      }
					}
				p.sendMessage(ChatColor.GRAY + "You have just removed " + ChatColor.RED + mobs + ChatColor.GRAY + " entities from " + ChatColor.RED + p.getWorld().getName());
				return true;
			}
			try {
					
			int removed = 0;
			
			final int radius = Integer.parseInt(args[0]);

			List<Entity> nearby = p.getNearbyEntities(radius, radius, radius);
			
			if (radius <= 0) {
				p.sendMessage(plugin.usage);
				p.sendMessage(ChatColor.GRAY + "Number must be above 0");
				return true;
			}
			for(Entity tmp : nearby){
			
			
		    	        if ((tmp instanceof LivingEntity)) {
		    	          if (!(tmp instanceof Player | tmp instanceof MushroomCow)) {    	    
					tmp.remove();
					removed++;
			
		    	          }
			      }
			}
			if (removed == 0) {
				p.sendMessage(ChatColor.GRAY + "There are no mobs in a radius of " + "'" + ChatColor.RED + radius + ChatColor.GRAY + "'" + ChatColor.GRAY + ".");
				return true;
			}
			if (removed == 1) {
				p.sendMessage(ChatColor.GRAY + "You have just killed " + ChatColor.RED + ChatColor.BOLD + removed + ChatColor.GRAY + " mob in a radius of " + ChatColor.RED + radius + ChatColor.GRAY + ".");
				return true;
			}
			p.sendMessage(ChatColor.GRAY + "You have just killed " +  ChatColor.RED + ChatColor.BOLD + removed + ChatColor.GRAY + " mobs in a radius of " + ChatColor.RED + radius + ChatColor.GRAY + ".");
			}
			   catch (Exception e) {
				      p.sendMessage(plugin.usage);
				      p.sendMessage(ChatColor.GRAY + "Please enter a number");
			   }

			}
		return true;
		}
}
