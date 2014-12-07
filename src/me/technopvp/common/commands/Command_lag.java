package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.api.LagApi;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Source(SourceType.ANY)
@Permissions(Permission.ANYONE)
public class Command_lag extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
			double tps = LagApi.getTPS();
			double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);

			sender.sendMessage(ChatColor.RED + "Server is currently running at " + Gold + tps + Red + " tps");
			sender.sendMessage(ChatColor.RED + "Lag is at about " + Gold + lag + Red + "%");
			return true;
	}

}
