package me.technopvp.common.commands;

import java.util.List;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.StringUtils;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_mobs extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("mobs.yes")) {
			noPermission();
			return true;
     	}
     	if (args.length == 0) {
        int mobs = 0;     
			List<Entity> nearby = player.getWorld().getEntities();			
			for(Entity tmp : nearby){				
		    	        if ((tmp instanceof LivingEntity)) {
		    	          if (!(tmp instanceof Player)) {    	    
					mobs++;
		    	          }
			      }
			}
			String command_mobs_message = StringUtils.translate(plugin.messages.getString("command_mobs_message"));
			command_mobs_message = command_mobs_message.replace("%mobs", "" + mobs);
			player.sendMessage(command_mobs_message);
     	}
		return true;		
}
}
