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

@Source(SourceType.PLAYER)
@Permissions(Permission.OWNER)
public class Command_lightning extends CommonCommand {
	dCommon plugin = dCommon.instance;

	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("lightning")) {
			if (!player.hasPermission("lightning.yes")) {
				showUsage(cmd);
				return true;	
			}
			if (args.length == 0) {
				player.getWorld().strikeLightning(player.getTargetBlock(null, 600).getLocation());
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == player) {
				target.getWorld().strikeLightning(target.getLocation());
				player.sendMessage(Gray + "You have smitted your self.");
				return true;
			}
			if (player.hasPermission("lightning.yes") || player.hasPermission("lightning.other")) {
				if (target == null) {
					Utils.targetOffline(player, args[0]);
					return true;
				}
			if (args.length == 1) {
				target.getWorld().strikeLightning(target.getLocation());
				player.sendMessage(Gray + "Smitting " + Red + target.getName() + Gray + ".");
				target.sendMessage(Gold + "You have been smited.");
				return true;
			}
		}else{
			player.sendMessage(plugin.other);
			return true;
		}
		}
		return true;
		
	}
}