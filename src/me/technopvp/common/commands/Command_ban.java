package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.utility.player.Ban;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Source(SourceType.ANY)
@Permissions(Permission.MOD)
public class Command_ban extends CommonCommand {
	dCommon plugin = dCommon.instance;

	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
        if (!sender.hasPermission("ban.yes")) {
            noPermission();
            return true;
        }
        if (args.length == 0) {
          showUsage(cmd);
          return true;
        }
        OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
        String msg = "";
        if (args.length > 1) {
            msg = "";
            for (int i = 1; i < args.length; i++) {
                msg += args[i] + " ";
            }
        }
//        if (Utils.isVip(target.getPlayer())) {
//        	sender.sendMessage(plugin.usage);
//        	sender.sendMessage(ChatColor.GRAY + "You are not allowed to ban " + target.getName());
//        	return true;
//        }
        if (args.length == 1) {
        	 new Ban(target.getName(), "Unspecified", sender.getName());
        	if (target.isOnline()) target.getPlayer().kickPlayer(ChatColor.RED + "You have been banned by " + sender.getName());
        	if (target.isOnline()) target.getPlayer().setHealth(0);
        	target.setBanned(true);
        	return true;
        }
        if (args.length >= 2) {
        	new Ban(target.getName(), msg, sender.getName());
            if (target.isOnline()) target.getPlayer().kickPlayer(ChatColor.RED + "You have been banned by " + sender.getName() + " for " + msg);
            if (target.isOnline()) target.getPlayer().setHealth(0);
            target.setBanned(true);
            return true;
		}
		return true;
	}
}
