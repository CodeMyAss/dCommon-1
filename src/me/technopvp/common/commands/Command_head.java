package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;
import me.technopvp.common.utilities.player.PlayerUtils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_head extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
        
		Player player = (Player)sender;
		if (!player.hasPermission("head.yes") || !player.hasPermission("playerhead.yes")) {
			PlayerUtils.permission(player);
			return true;
		}
			if(args.length == 0) {
				showUsage(cmd);
				return true;
			} else {
				if(args.length == 1) {			
				ItemStack skullitem = new ItemStack(Material.SKULL_ITEM);			
				skullitem.setDurability((short)3);			
				SkullMeta skullMeta = (SkullMeta)skullitem.getItemMeta();
				skullMeta.setOwner(args[0]);					
				skullitem.setItemMeta(skullMeta);				
				player.getInventory().addItem(skullitem);
				player.sendMessage(ChatColor.GRAY + "You now have " + ChatColor.RED + args[0] + ChatColor.GRAY + "§c's §7head in your inventory!");
				}
			}
			return true;		
			}
}
