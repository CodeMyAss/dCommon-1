package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@Source(SourceType.ANY)
@Permissions(Permission.ADMIN)
public class Command_crash extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, final String[] args) {
		Player player = (Player) sender;
		if (!player.hasPermission("crash.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		if (args.length == 1) {
			@SuppressWarnings("deprecation")
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				targetOffline(args[0]);
				return true;
			}
			Inventory courrouptInventory = Bukkit.createInventory(target, 666);
			target.openInventory(courrouptInventory);
			player.sendMessage(Red + "Crashing " + target.getName());
			return true;
		}
		return true;
	}
}