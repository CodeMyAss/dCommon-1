package me.technopvp.common.commands;

import java.util.ArrayList;
import java.util.List;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_lore extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("lore.yes")) {
			noPermission();
			return true;
		}
		
		if ((player.getItemInHand() != null)
				&& (!player.getItemInHand().getType().equals(Material.AIR))) {
			if (args.length == 1) {
				ItemStack itemStack = player.getItemInHand();

				String string = args[0];
				String colouredString = ChatColor.translateAlternateColorCodes('&', string);
				String spacedString = colouredString.replaceAll("_", " ");
				String[] loreNotConverted = spacedString.split(";");                         
				List<String> lore = new ArrayList<String>();
				for (String s : loreNotConverted) {
					lore.add(s);
				}

				ItemMeta itemStackMeta = itemStack.getItemMeta();
				itemStackMeta.setLore(lore);

				itemStack.setItemMeta(itemStackMeta);

				player.sendMessage(ChatColor.GRAY + "Succesfully set the lore of the item in your hand to: " + ChatColor.RED + colouredString);
						  
						
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
