package me.technopvp.common.commands;

import me.technopvp.common.utilities.Utils;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.ADMIN)

public class Command_heal extends CommonCommand	{

	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		if (!player.hasPermission("heal.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			player.setHealth(player.getMaxHealth());
			player.sendMessage(Gray + "You have been healed.");
			return true;
		}
		if (args.length == 1) {
		Player t = Bukkit.getPlayer(args[0]);
		if (t == player) {
			player.setHealth(player.getMaxHealth());
			player.sendMessage(Gray + "You have been healed.");
			return true;
		}
		if (player.hasPermission("heal.yes") || player.hasPermission("heal.other")) {
			if (t == null) {
				Utils.targetOffline(player, args[0]);
				return true;
			}
			t.setHealth(player.getMaxHealth());
			t.sendMessage(Gray + "You have been healed.");
			player.sendMessage(Gray + "You have healed " + Red + t.getName() + Gray + ".");
			return true;
	}else{
		player.sendMessage(Gray + "You do not have permission to heal other players");
		return true;
	}
		}
		return true;
	}
}
