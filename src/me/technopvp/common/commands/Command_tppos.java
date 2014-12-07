package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_tppos extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("teleport.yes") || !player.hasPermission("tp.yes") || !player.hasPermission("tppos.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		if (args.length < 3) {
			msg("Invalid Arguments");
			showUsage(cmd);
			return true;
		}
		Integer x = Integer.parseInt(args[0]);
		Integer y = Integer.parseInt(args[1]);
		Integer z = Integer.parseInt(args[2]);
		
		if (args.length == 3) {
			player.teleport(new Location(player.getWorld(), x, y, z));
			msg("You have teleported to " + Red + x + Gray + ", " + Red + y + Gray + ", " + Red + z + Gray + ".");
			return true;
		}
		
		return true;
		
}
}
