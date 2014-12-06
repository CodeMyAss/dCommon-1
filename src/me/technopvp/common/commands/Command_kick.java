package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.HELPER)
public class Command_kick extends CommonCommand{
	dCommon plugin = dCommon.instance;
	
	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (!sender.hasPermission("kick.yes")) {
				noPermission();
				return true;
			}
			if (args.length == 0) {
				showUsage(cmd);
				return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage(ChatColor.RED + "Could not find player " + args[0] + "!");			
				return true;
			}

			String msg = "";

			if (args.length > 1) {
				msg = " for ";
				for (int i = 1; i < args.length; i++) {
					msg = msg + args[i] + " ";
				}
			}
			String KickReason = plugin.getConfig().getString("config.KickReason");
			KickReason = KickReason.replaceAll("&", "§");
			KickReason = KickReason.replaceAll("%player", sender.getName());
			KickReason = KickReason.replaceAll("%reason", msg);
			KickReason = KickReason.replaceAll("%target", target.getName());
			
			String KickBroadcastMessage = plugin.getConfig().getString("config.KickBroadcastMessage");
			KickBroadcastMessage = KickBroadcastMessage.replaceAll("&", "§");
			KickBroadcastMessage = KickBroadcastMessage.replaceAll("%player", sender.getName());
			KickBroadcastMessage = KickBroadcastMessage.replaceAll("%reason", msg);
			KickBroadcastMessage = KickBroadcastMessage.replaceAll("%target", target.getName());
			
			target.kickPlayer(KickReason);					
			Bukkit.broadcastMessage(KickBroadcastMessage);					
			return true;
		}
	}
