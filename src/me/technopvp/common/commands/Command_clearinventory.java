package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;
import me.technopvp.common.utilities.player.InventoryUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_clearinventory extends CommonCommand {
	dCommon plugin = dCommon.instance;	
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
	
		if (!player.hasPermission("clearinventory.yes") || !player.hasPermission("ci.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			InventoryUtils.clearInventory(player);
			player.sendMessage(ChatColor.GOLD + "Inventory cleared");
			return true;
		}
		if (args[0].equalsIgnoreCase("full") | args[0].equalsIgnoreCase("all")) {
			InventoryUtils.clearArmor(player);
			player.sendMessage(Gold + "You have cleared your inventory and armor");						
			return true;
		}
		@SuppressWarnings("deprecation")
		Player target = Bukkit.getServer().getPlayer(args[0]);
		if (target == null) {
			player.sendMessage(ChatColor.RED + "Invaled Arguments");
			player.sendMessage(ChatColor.GRAY + "Could not find player " + "'" + args[0] + "'");						
			return true;
		}
		if (args.length == 1) {
			target.getInventory().clear();
			player.sendMessage(ChatColor.GOLD + "You have cleard " + target.getDisplayName() + "'s inventory");						
			return true;
		}
		return true;
	}
}
