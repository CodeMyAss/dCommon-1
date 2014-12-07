package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_quickgm extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {	
			Player p = (Player)sender;
					
			if (!p.hasPermission("gm.yes")) {
				noPermission();
				return true;
			}
			
			if (args.length == 0) {
				if (p.getGameMode() == GameMode.CREATIVE) {
					p.setGameMode(GameMode.SURVIVAL);
					p.sendMessage(ChatColor.GRAY + "Your gamemode has been set to " + ChatColor.RED + ChatColor.BOLD + p.getGameMode().toString().toLowerCase() + ChatColor.GRAY + ".");
					return true;
				}
				if (p.getGameMode() == GameMode.SURVIVAL) {
					p.setGameMode(GameMode.CREATIVE);
					p.sendMessage(ChatColor.GRAY + "Your gamemode has been set to " + ChatColor.RED + ChatColor.BOLD + p.getGameMode().toString().toLowerCase() + ChatColor.GRAY + ".");
					return true;
				}
				if (p.getGameMode() == GameMode.ADVENTURE) {
					p.setGameMode(GameMode.CREATIVE);
					p.sendMessage(ChatColor.GRAY + "Your gamemode has been set to " + ChatColor.RED + ChatColor.BOLD + p.getGameMode().toString().toLowerCase() + ChatColor.GRAY + ".");
					return true;
				}
				}
			
			if (args.length == 1) {

				if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")){	
					p.setGameMode(GameMode.SURVIVAL);
					p.sendMessage(ChatColor.GRAY + "Your gamemode has been set to " + ChatColor.RED + ChatColor.BOLD + p.getGameMode().toString().toLowerCase() + ChatColor.GRAY + ".");
					return true;
				
				}
			
				if(args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){				
					p.setGameMode(GameMode.CREATIVE);
					p.sendMessage(ChatColor.GRAY + "Your gamemode has been set to " + ChatColor.RED + ChatColor.BOLD + p.getGameMode().toString().toLowerCase() + ChatColor.GRAY + ".");
					return true;
				
				}
			
				if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){				
					p.setGameMode(GameMode.ADVENTURE);
					p.sendMessage(ChatColor.GRAY + "Your gamemode has been set to " + ChatColor.RED + ChatColor.BOLD + p.getGameMode().toString().toLowerCase() + ChatColor.GRAY + ".");
					return true;
				
				}
				return true;
			}
		return true;
	}
}
