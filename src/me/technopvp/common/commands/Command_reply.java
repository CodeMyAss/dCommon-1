package me.technopvp.common.commands;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Source(SourceType.ANY)
@Permissions(Permission.ANYONE)
public class Command_reply extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (args.length == 0) {
			if (Lists.reply.containsKey(sender.getName())) {
				sender.sendMessage(Gray + "You are currently in a conversation with " + Lists.reply.get(sender.getName()) + ".");
			}else {
				sender.sendMessage(Red + "You have never privately messaged anyone.");
				return true;
			}
			return true;
		}
		if (args.length >= 1) {
			if (Lists.reply.containsKey(sender.getName())) {
			String msg = "";   
			msg = "";
			for (String arg : args) {
				msg = msg + arg + " ";
			}

			msg = msg.substring(0, msg.length() - 1);
			
				String target = Lists.reply.get(sender.getName());
				
				if (target == sender.getName()) {
					sender.sendMessage(Gray + "<" + sender.getName() + " -> " + target + "> " + Red + msg);
				} else if (sender.isOp()) {
					sender.sendMessage(Gray + "<" + sender.getName() + " -> " + target + "> " + Red + msg);
					Bukkit.getPlayer(target).sendMessage(Gray + "<" + sender.getName() + " -> " + target + "> " + Red + msg);
					return true;
				}else {
				
				
					sender.sendMessage(Gray + "<" + sender.getName() + " -> " + target + "> " + msg);
				Bukkit.getPlayer(target).sendMessage(Gray + "<" + sender.getName() + " -> " + target + "> " + msg);
				return true;
				}
		}else {
			if (Lists.reply.containsKey(sender.getName())) {
				sender.sendMessage(Gray + "You are currently in a conversation with " + Lists.reply.get(sender.getName()) + ".");
			}else {
				sender.sendMessage(Red + "You have never privately messaged anyone.");
				return true;
			}
		}
			return true;
		}
		return true;
	}

}
