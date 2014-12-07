package me.technopvp.common.commands;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.HELPER)
public class Command_staffchat extends CommonCommand{
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("staffchat.yes")) {
			player.sendMessage(plugin.nstaff); 
			return true;
		}

		if (args.length == 0) {
			if (Lists.staffchat.contains(player.getName()))
				Lists.staffchat.remove(player.getName());
			else
				Lists.staffchat.add(player.getName());

			player.sendMessage(ChatColor.RED + "You are " + (Lists.staffchat.contains(player.getName()) ? "now in" : "no longer in") + " staff chat mode.");
					
					
		} else {
			String msg = "";

			for (String arg : args) {
				msg = msg + arg + " ";
			}

			for (Player all : Bukkit.getServer().getOnlinePlayers()) {
				if (all.hasPermission("staffchat.yes"))
					all.sendMessage(Aqua + player.getName() + ": " + msg);
			}
		}
		return true;
	}
	}
