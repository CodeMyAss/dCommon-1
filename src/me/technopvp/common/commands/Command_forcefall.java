package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.OWNER)
public class Command_forcefall extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("forcefall.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		@SuppressWarnings("deprecation")
		final Player target = Bukkit.getServer().getPlayer(args[0]);
		if (target == null) {
			showUsage(cmd);
			player.sendMessage(ChatColor.RED + "Could not find player " + args[0]);						
			return true;
		}
		player.sendMessage(Gray + "You have forcefalled " + target.getName());
		player.getLocation().setY((500.0D));
		target.teleport(player.getLocation());
		target.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 1, 1);
		return true;		
}
}
