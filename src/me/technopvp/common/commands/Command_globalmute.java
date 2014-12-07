package me.technopvp.common.commands;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.StringUtils;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Source(SourceType.ANY)
@Permissions(Permission.ADMIN)
public class Command_globalmute extends CommonCommand {
	dCommon plugin = dCommon.instance;	
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (sender.hasPermission("globalmute.yes")) {
			if (args.length == 0) {
				if (!Lists.muteall) {
					Lists.muteall = true;
					String chat_muted = StringUtils.translate(plugin.messages.getString("chat_muted"));
					chat_muted = chat_muted.replace("%player", sender.getName());
					Bukkit.broadcastMessage(chat_muted);																																
					return true;
				}
				Lists.muteall = false;
				String chat_unmuted = StringUtils.translate(plugin.messages.getString("chat_unmuted"));
				chat_unmuted = chat_unmuted.replace("%player", sender.getName());
				Bukkit.broadcastMessage(chat_unmuted);														
			}
		} else {
			noPermission();
			return true;
		}
		return true;
	}
}
