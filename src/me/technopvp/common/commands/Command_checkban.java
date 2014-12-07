package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.managers.MessageManager;
import me.technopvp.common.utilities.enums.Level;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Permissions.Permission;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.player.Ban;
import me.technopvp.common.utilities.player.Ban.BanInfo;
import me.technopvp.common.utilities.player.User;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Source(SourceType.ANY)
@Permissions(Permission.ANYONE)
public class Command_checkban extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (!sender.hasPermission("checkban.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		@SuppressWarnings("deprecation")
		OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
		if (target.isBanned()) {
	        if (Ban.isBanned(target.getName())) {
	         sender.sendMessage(Gray + "Player " + Red + target.getName() + Gray + " is banned for " + Red + User.getBanInformation(target.getName(), BanInfo.REASON) + Gray + " by " + Red + User.getBanInformation(target.getName(), BanInfo.BY) + Gray + ".");
	         return true;
	      }
	        try {
	    	  sender.sendMessage(Gray + "Player " + Red + target.getName() + Gray + " is banned.");
	    	  return true;
	      } catch (Exception e) {
	    	  MessageManager.log(Level.LOW, target.getName() + "'s ban file is corrupted, command ran by " + sender.getName());
	      }
	    }else {
	    	if (target.hasPlayedBefore()) {
	    	sender.sendMessage(Gray + "Player " + Red + target.getName() + Gray + " is not banned, and has a clean record.");
	    	} else {
	    		sender.sendMessage(Gray + "Player " + Red + target.getName() + Gray + " has never played before, and is not banned.");
	    		return true;
	    	}
	    	return true;
	    }
		return true;
}
}
