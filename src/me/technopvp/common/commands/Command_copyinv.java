package me.technopvp.common.commands;

import java.util.HashMap;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.StringUtils;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_copyinv extends CommonCommand {
	dCommon plugin = dCommon.instance;	
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
	
		if (!player.hasPermission("copyinv.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		if (args.length == 1) {
			@SuppressWarnings("deprecation")
			Player all = Bukkit.getPlayer(args[0]);
			if (all == null) {
				player.sendMessage(ChatColor.RED + "That player does not exist!");
			} else {
				ItemStack[] armor = all.getInventory().getArmorContents();
				player.getInventory().clear();
				player.getInventory().setArmorContents(null);
				player.getInventory().setArmorContents(armor);
				ItemStack[] inv = all.getInventory().getContents();
				HashMap<Player, ItemStack[]> itemhash = new HashMap<Player, ItemStack[]>();
				itemhash.put(player, inv);
				ItemStack[] items = itemhash.get(player);
				player.getInventory().setContents(items);
				String copyinv_message = StringUtils.translate(plugin.messages.getString("copyinv_message"));
				copyinv_message = copyinv_message.replace("%target", all.getName());
				copyinv_message = copyinv_message.replace("%player", player.getName());
				player.sendMessage(copyinv_message);							

			}
		}
		return true;
	}
}
