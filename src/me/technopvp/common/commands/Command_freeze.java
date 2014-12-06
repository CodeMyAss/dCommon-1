package me.technopvp.common.commands;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.utility.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_freeze extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("freeze.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		Player target = Bukkit.getServer().getPlayer(args[0]);
		if (target == null) {
			invalidUsage();
			sender.sendMessage(ChatColor.GRAY + "Could not find player " + "'" + args[0] + "'");						
			return true;
		}
	     if (Utils.isVip(target)) {
	    	 invalidUsage();
	    	 player.sendMessage(Gray + "You can not freeze " + target.getName());
            	return true;
            }
		if (Lists.frozen.contains(target.getName())) {
			Lists.frozen.remove(target.getName());
			sender.sendMessage(Gray + "You have unfroze " + ChatColor.RED + target.getName());						
			return true;
		}
		Lists.frozen.add(target.getName());
		sender.sendMessage(Gray + "You have froze " + ChatColor.RED + target.getName());	
		return true;
		
}
}
