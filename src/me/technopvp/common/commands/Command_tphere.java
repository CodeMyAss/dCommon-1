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
@Permissions(Permission.ANYONE)
public class Command_tphere extends CommonCommand {

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("tphere.yes") || !player.hasPermission("teleporthere.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		if (args.length >= 1) {
			@SuppressWarnings("deprecation")
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				targetOffline(args[0]);
				return true;
			}
			
			target.teleport(player.getLocation());
			
			player.sendMessage(Gray + "Teleporting....");
			target.sendMessage(Gray + "Teleporting....");
			return true;
		}
		
		return true;
	}

}
