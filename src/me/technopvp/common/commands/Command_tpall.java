package me.technopvp.common.commands;

import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.OWNER)
public class Command_tpall extends CommonCommand {

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("tpall.yes") || !player.hasPermission("teleport.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			for (Player allPlayers : Bukkit.getOnlinePlayers()) {
				if (allPlayers != player) {
					allPlayers.teleport(player.getLocation());
				}
			}
			player.sendMessage(Gray + "Server has been teleported to your location");
			return true;
		}
		if (args.length >= 1) {
			@SuppressWarnings("deprecation")
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				targetOffline(args[0]);
				return true;
			}
			for (Player allPlayers : Bukkit.getOnlinePlayers()) {
				if (allPlayers != target) {
					allPlayers.teleport(target.getLocation());
				}
			}
			player.sendMessage(Gray + "Server has been teleported to " + Red + target.getName() + "'s " + Gray + " location.");
			return true;
		} 
		return true;
	}
	
	

}
