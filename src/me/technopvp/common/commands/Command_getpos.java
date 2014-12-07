package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.Utils;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_getpos extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		if (!player.hasPermission("getpos.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			player.sendMessage(Gray + "Current World: " + Red + player.getWorld().getName());
			player.sendMessage(Gray + "X: " + Red + player.getLocation().getBlockX());
			player.sendMessage(Gray + "Y: " + Red + player.getLocation().getBlockY());
			player.sendMessage(Gray + "Z: " + Red + player.getLocation().getBlockZ());
			player.sendMessage(Gray + "Pitch: " + Red + Math.round(player.getLocation().getPitch()) + Gray +  " (" + Red + "Head Angle" + Gray + ")");
			player.sendMessage(Gray + "Yaw: " + Red +Math.round(player.getLocation().getYaw() + 180 + 360 % 360) + Gray + " (" + Red + "Rotation" + Gray + ")");
			return true;
		}
		@SuppressWarnings("deprecation")
		Player t = Bukkit.getPlayer(args[0]);
		if (t == player) {
			player.sendMessage(Gray + "Current World: " + Red + player.getWorld().getName());
			player.sendMessage(Gray + "X: " + Red + player.getLocation().getBlockX());
			player.sendMessage(Gray + "Y: " + Red + player.getLocation().getBlockY());
			player.sendMessage(Gray + "Z: " + Red + player.getLocation().getBlockZ());
			player.sendMessage(Gray + "Pitch: " + Red + Math.round(player.getLocation().getPitch()) + Gray +  " (" + Red + "Head Angle" + Gray + ")");
			player.sendMessage(Gray + "Yaw: " + Red +Math.round(player.getLocation().getYaw() + 180 + 360 % 360) + Gray + " (" + Red + "Rotation" + Gray + ")");
			return true;
		}
		if (player.hasPermission("getpos.yes") || player.hasPermission("getpos.other")) {
			if (t == null) {
				Utils.targetOffline(player, args[0]);
				return true;
			}
		if (args.length == 1) {
			player.sendMessage(Gray + "Current World: " + Red + t.getWorld().getName());
			player.sendMessage(Gray + "X: " + Red + t.getLocation().getBlockX());
			player.sendMessage(Gray + "Y: " + Red + t.getLocation().getBlockY());
			player.sendMessage(Gray + "Z: " + Red + t.getLocation().getBlockZ());
			player.sendMessage(Gray + "Pitch: " + Red + Math.round(t.getLocation().getPitch()) + Gray +  " (" + Red + "Head Angle" + Gray + ")");
			player.sendMessage(Gray + "Yaw: " + Red +Math.round(t.getLocation().getYaw() + 180 + 360 % 360) + Gray + " (" + Red + "Rotation" + Gray + ")");
			return true;
		}
	}else{
		player.sendMessage(plugin.other);
		return true;
	}
		return true;
	}

}
