package me.technopvp.common.commands;

import me.technopvp.common.utilities.config.SpawnConfig;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.OWNER)
public class Command_setspawn extends CommonCommand {

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player) sender;
		Location playerLocation = player.getLocation();

		if (args.length == 0) {
			SpawnConfig.getInstance().setSpawn("default", player.getWorld().getName(),
					playerLocation.getX(),
					playerLocation.getY(),
					playerLocation.getZ(),
					playerLocation.getPitch(),
					playerLocation.getYaw());

			player.sendMessage(Red + "Default " + Gray + "spawn location has been set at "
					+ playerLocation.getBlockX() + ", "
					+ playerLocation.getBlockY() + ", "
					+ playerLocation.getBlockZ() + ".");
			return true;
		}
		if (args.length == 1) {
			String spawnName = "";
			for (String arg : args) {
				spawnName = spawnName + arg + " ";
			}

			spawnName = spawnName.substring(0, spawnName.length() - 1);

			SpawnConfig.getInstance().setSpawn(spawnName, player.getWorld().getName(),
					playerLocation.getX(),
					playerLocation.getY(),
					playerLocation.getZ(),
					playerLocation.getPitch(),
					playerLocation.getYaw());

			player.sendMessage(Red + spawnName + Gray + " spawn location has been set at "
					+ playerLocation.getBlockX() + ", "
					+ playerLocation.getBlockY() + ", "
					+ playerLocation.getBlockZ() + ".");

			return true;
		}

		return true;
	}
}
