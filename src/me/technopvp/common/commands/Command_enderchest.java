package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.utility.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_enderchest extends CommonCommand {
	dCommon plugin = dCommon.instance;

	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		if (!player.hasPermission("enderchest.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			player.openInventory(player.getEnderChest());
			return true;
		}
		Player t = Bukkit.getPlayer(args[0]);
		if (player.hasPermission("enderchest.yes") || player.hasPermission("enderchest.other")) {
		if (t == null) {
			Utils.targetOffline(player, args[0]);
			return true;
		}
		Inventory inv = Bukkit.createInventory(player, 27, Red + player.getName() + "'s Ender Chest.");
		inv.setContents(t.getEnderChest().getContents());
		player.sendMessage(Gray + "You have opend " + Red + t.getName() + Gray + " ender chest.");
		player.openInventory(inv);
		return true;
	}else {
		player.sendMessage(plugin.other);
	}
		return true;
	}
}