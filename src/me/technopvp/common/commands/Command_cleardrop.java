package me.technopvp.common.commands;

import java.util.List;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_cleardrop extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		if (!player.hasPermission("cleardrop.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
		}
		if (args.length == 1) {
			try {
				final int radius = Integer.parseInt(args[0]);
				List<Entity> nearby = player.getNearbyEntities(radius, radius , radius);						
				if (radius <= 0) {
					player.sendMessage(plugin.usage);
					player.sendMessage(Gray + "Number must be above 0");
					return true;
				}
				for (Entity tmp : nearby) {
					if (tmp instanceof LivingEntity
							| tmp instanceof ItemFrame) {
					}else {
						tmp.remove();
					}
				}
				Bukkit.broadcastMessage(ChatColor.RED + player.getName() + ChatColor.GRAY + " has just removed all entities in a radius of " + ChatColor.RED + radius);					
			} catch (Exception e) {
				player.sendMessage(plugin.usage);
				player.sendMessage(Gray + "Please enter a number");
			}
		}
		return true;
	}
}	
