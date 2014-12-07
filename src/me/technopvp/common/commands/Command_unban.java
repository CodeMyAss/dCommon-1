package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.StringUtils;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;
import me.technopvp.common.utilities.player.User;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Source(SourceType.ANY)
@Permissions(Permission.ADMIN)
public class Command_unban extends CommonCommand {
	dCommon plugin = dCommon.instance;	
	
	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
				if (!sender.hasPermission("unban.yes") || !sender.hasPermission("pardon.yes")) {
					noPermission();
					return true;
				}
				if (args.length == 0) {
					showUsage(cmd);
					return true;	
				}
				OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
				if (!target.isBanned()) {
					sender.sendMessage(plugin.usage);
					String not_banned = StringUtils.translate(plugin.messages.getString("not_banned"));
					not_banned = not_banned.replace("%target", target.getName());
					sender.sendMessage(not_banned);
					return true;
				}
				 User.setBanned(target.getName(), false);
				 plugin.user.getUser(target.getName()).getPlayerConfig().getConfig().set("Ban.Reason", null);
				 plugin.user.getUser(target.getName()).getPlayerConfig().getConfig().set("Ban.BannedBy", null);
				 plugin.user.getUser(target.getName()).getPlayerConfig().savePlayerConfig();
				 
				target.setBanned(false);
				
				String unban_message = StringUtils.translate(plugin.messages.getString("unban_message"));
				unban_message = unban_message.replace("%target", target.getName());
				Bukkit.broadcastMessage(unban_message);	
				return true;			
	}
}
