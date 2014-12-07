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
@Permissions(Permission.ADMIN)
public class Command_top extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		if (!player.hasPermission("top.yes") || !player.hasPermission("up.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
		Location top = player.getLocation();
		double topy = 0;
		for(int i = 0; i < top.getWorld().getMaxHeight(); i++)
			if((new Location(top.getWorld(), top.getX(), i, top.getZ())).getBlock().getType().isSolid()) {
					topy = i + 1;
			}
		player.teleport(new Location(top.getWorld(), top.getX(), topy, top.getZ(), top.getYaw(), top.getPitch()));
		msg("Teleporting to top.");
		return true;
		
		}
		return true;
}
}
