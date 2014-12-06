package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_flyspeed extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("flyspeed.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		float speed = Float.valueOf(args[0]);
		float defaultSpeed = 0.1f;
		if (speed < 0.1 || speed >= 10.1) {
			invalidImput();
			msg(Gray + "Your speed must be above 0, and below 10");
			return true;
		}
		
		if (args.length == 1) {
			player.setFlySpeed(defaultSpeed * speed);
			msg(Gray + "You have set your fly speed to " + Red + speed + Gray + ".");
			return true;
		}
		if (args.length == 2) {
			Player target = Bukkit.getPlayer(args[1]);
			if (target == null) {
				targetOffline(args[1]);
				return true;
			}
			
			target.setFlySpeed(defaultSpeed * speed);
			msg(Gray + "You have set the fly speed for " + Red + target.getName() + Gray + " to " + Red + speed + Gray + ".");
			return true;
		}
		return true;
		
}
}
