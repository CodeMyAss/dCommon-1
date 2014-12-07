package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Permissions.Permission;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.player.Ban;

import org.apache.commons.lang.StringUtils;
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
		if (args.length >= 1) {
			OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
			String reason = StringUtils.join(args, ' ', 1, args.length);

			new Ban(target, (reason.isEmpty() ? "Unspecified": reason), sender.getName());
			if (target.isOnline()) target.getPlayer().kickPlayer(ChatColor.RED + "You have been banned by " + sender.getName() + (!reason.isEmpty() ? " for " + reason : "") + ".");
			if (target.isOnline()) target.getPlayer().setHealth(0);
			return true;
		}
		return true;
	}
}
