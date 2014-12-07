package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.StringUtils;
import me.technopvp.common.utilities.Utils;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.ADMIN)
public class Command_kill extends CommonCommand	{
	dCommon plugin = dCommon.instance;
	
	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		String command_kill_message = StringUtils.translate(plugin.messages.getString("command_kill_message"));
		command_kill_message = command_kill_message.replace("%player", player.getName());
		if (args.length == 0) {
			if (player.hasPermission("deathcords.yes")) {
				player.setHealth(0);
				return true;
			}
		player.setHealth(0);
		player.sendMessage(command_kill_message);
		return true;
		}
		if (args.length >= 1) {
			if (!player.hasPermission("kill.other")) {
				player.sendMessage(Gray + "You do not have permission to kill other players.");
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				Utils.targetOffline(player, args[0]);
				return true;
			}
			if (target == player) {
				if (!player.hasPermission("deathcords.yes")) {
				player.setHealth(0);
				player.sendMessage(command_kill_message);
			}else {
				player.setHealth(0);
			}
				return true;
			}
			if (target.getName().equals("TechnoPvP") || target.getName().equals("tpvp")) {
				player.sendMessage(plugin.usage);
				player.sendMessage(Gray + "You can not kill " + target.getName() + ".");
				return true;
			}
				target.setHealth(0);
				String command_kill_target_message = StringUtils.translate(plugin.messages.getString("command_kill_target_message"));
				command_kill_target_message = command_kill_target_message.replace("%player", player.getName());
				command_kill_target_message = command_kill_target_message.replace("%target", target.getName());
				player.sendMessage(command_kill_target_message);
				target.sendMessage(command_kill_message);
				return true;
		}
		return true;
	}

}
