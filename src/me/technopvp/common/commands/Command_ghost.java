package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.StringUtils;
import me.technopvp.common.utilities.Utils;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;
import me.technopvp.common.utilities.player.PlayerUtils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ADMIN)
public class Command_ghost extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
			if (!player.hasPermission("ghost.yes")) {
				PlayerUtils.permission(player);
				return true;
			}
			String ghost_mode_disable_message = StringUtils.translate(plugin.messages.getString("ghost_mode_disable_message"));
			ghost_mode_disable_message = ghost_mode_disable_message.replace("%player", player.getName());
			
			String ghost_mode_enable_message = StringUtils.translate(plugin.messages.getString("ghost_mode_enable_message"));
			ghost_mode_enable_message = ghost_mode_enable_message.replace("%player", player.getName());
			
			
			if (args.length == 0) {
			if (!plugin.ghostManager.isGhost(player)) {
				plugin.ghostManager.setGhost(player, true);
				player.sendMessage(ghost_mode_enable_message);;
				return true;
			}else  {
				plugin.ghostManager.setGhost(player, false);
				player.sendMessage(ghost_mode_disable_message);
				return true;
			}
			}
			if (args.length == 1) {
				@SuppressWarnings("deprecation")
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					Utils.targetOffline(player, args[0]);
					return true;
				}
				if (target == player) {
					if (!plugin.ghostManager.isGhost(player)) {
						plugin.ghostManager.setGhost(player, true);
						player.sendMessage(ghost_mode_enable_message);;
						return true;
					}else  {
						plugin.ghostManager.setGhost(player, false);
						player.sendMessage(ghost_mode_disable_message);
						return true;
					}
				}
				String target_enable_ghost_mode = StringUtils.translate(plugin.messages.getString("target_enable_ghost_mode"));
				target_enable_ghost_mode = target_enable_ghost_mode.replace("%player", player.getName());
				target_enable_ghost_mode = target_enable_ghost_mode.replace("%target", target.getName());
				
				String target_disable_ghost_mode = StringUtils.translate(plugin.messages.getString("target_disable_ghost_mode"));
				target_disable_ghost_mode = target_disable_ghost_mode.replace("%player", player.getName());
				target_disable_ghost_mode = target_disable_ghost_mode.replace("%target", target.getName());
				if (!plugin.ghostManager.isGhost(target)) {
					plugin.ghostManager.setGhost(target, true);
					player.sendMessage(target_enable_ghost_mode);
					target.sendMessage(ghost_mode_enable_message);
				}else {
					plugin.ghostManager.setGhost(target, false);
					target.sendMessage(ghost_mode_disable_message);
					player.sendMessage(target_disable_ghost_mode);
					return true;
				}
			}
			return true;
	}

}
