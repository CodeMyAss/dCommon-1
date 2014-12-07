package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_itemname extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("itemname.yes")) {
			noPermission();
			return true;
		}
		if ((player.getItemInHand() != null)
				&& (!player.getItemInHand().getType().equals(Material.AIR))) {
			if (args.length == 1) {
				ItemStack itemStack = player.getItemInHand();

				String string = args[0];
				String spacedString = string.replaceAll("_", " ");
				String colouredString = ChatColor.translateAlternateColorCodes('&', spacedString);
						

				ItemMeta itemStackMeta = itemStack.getItemMeta();
				itemStackMeta.setDisplayName(colouredString);

				itemStack.setItemMeta(itemStackMeta);

				player.sendMessage(ChatColor.GRAY + "Succesfully renamed the item in hand to: " + ChatColor.RED + colouredString);													
			} else {
				showUsage(cmd);
			}
		} else {
			invalidUsage();
			player.sendMessage(ChatColor.GRAY + "You are not holding an item in your hand");					
		}
		return true;		
}
}
