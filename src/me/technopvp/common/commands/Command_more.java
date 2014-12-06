package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_more extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		if (!player.hasPermission("more.yes")) {
			noPermission();
			return true;
		}
		
		if (!player.getItemInHand().getType().equals(Material.AIR)) {
			player.getItemInHand().setAmount(64);
			player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
		}else {
			invalidUsage();
			player.sendMessage(Gray + "You must be holding an item.");
			return true;
		}
		return true;
		
		
}
}
