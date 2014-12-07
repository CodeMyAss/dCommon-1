package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;
import me.technopvp.common.utilities.player.PlayerUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_compass extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
	Player p = (Player)sender;
		if (!p.hasPermission("compass.yes")) {
			PlayerUtils.permission(p);
			return true;
		}
		
		int bearing = (int)(p.getLocation().getYaw() + 180.0F + 360.0F) % 360;
	    if (bearing < 23)
	    {
	    	p.sendMessage(ChatColor.GRAY + "Direction:" + ChatColor.RED + " N " + ChatColor.GRAY + "(" + ChatColor.RED + Integer.valueOf(bearing) + ChatColor.GRAY + " degress).");
	    }
	    else
	    {
	      if (bearing < 68)
	      {
	    	  p.sendMessage(ChatColor.GRAY + "Direction:" + ChatColor.RED + " NE " + ChatColor.GRAY + "(" + ChatColor.RED + Integer.valueOf(bearing) + ChatColor.GRAY + " degress).");
	      }
	      else
	      {
	        if (bearing < 113)
	        {
	        	p.sendMessage(ChatColor.GRAY + "Direction:" + ChatColor.RED + " E " + ChatColor.GRAY + "(" + ChatColor.RED + Integer.valueOf(bearing) + ChatColor.GRAY + " degress).");
	        }
	        else
	        {
	          if (bearing < 158)
	          {
	        	  p.sendMessage(ChatColor.GRAY + "Direction:" + ChatColor.RED + " SE " + ChatColor.GRAY + "(" + ChatColor.RED + Integer.valueOf(bearing) + ChatColor.GRAY + " degress).");
	          }
	          else
	          {
	            if (bearing < 203)
	            {
	            	p.sendMessage(ChatColor.GRAY + "Direction:" + ChatColor.RED + " S " + ChatColor.GRAY + "(" + ChatColor.RED + Integer.valueOf(bearing) + ChatColor.GRAY + " degress).");
	            }
	            else
	            {
	              if (bearing < 248)
	              {
	            	  p.sendMessage(ChatColor.GRAY + "Direction:" + ChatColor.RED + " SW " + ChatColor.GRAY + "(" + ChatColor.RED + Integer.valueOf(bearing) + ChatColor.GRAY + " degress).");
	              }
	              else
	              {
	                if (bearing < 293)
	                {
	                	p.sendMessage(ChatColor.GRAY + "Direction:" + ChatColor.RED + " W " + ChatColor.GRAY + "(" + ChatColor.RED + Integer.valueOf(bearing) + ChatColor.GRAY + " degress).");
	                }
	                else
	                {
	                  if (bearing < 338)
	                  {
	                	  p.sendMessage(ChatColor.GRAY + "Direction:" + ChatColor.RED + " NW " + ChatColor.GRAY + "(" + ChatColor.RED + Integer.valueOf(bearing) + ChatColor.GRAY + " degress).");
	                  }
	                  else
	                  {
	                	  p.sendMessage(ChatColor.GRAY + "Direction:" + ChatColor.RED + " N " + ChatColor.GRAY + "(" + ChatColor.RED + Integer.valueOf(bearing) + ChatColor.GRAY + " degress).");
	                  }
	                }
	              }
	            }
	          }
	        }
	      }
	    }
	return true;
}
}