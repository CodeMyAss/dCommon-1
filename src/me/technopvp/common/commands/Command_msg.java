package me.technopvp.common.commands;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.Utils;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.ANYONE)
public class Command_msg extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		/*TODO Finish Social Spy.*/
			if (args.length == 0) {
				showUsage(cmd);
				return true;
			}
			try {
			Player target = Bukkit.getPlayer(args[0]);
			if (args.length == 1) {
				if (target == null) {
					targetOffline(args[0]);
					return true;
				}
				return true;
			}
			if (args.length >= 2) {
				String msg = "";
	            for (int i = 1; i < args.length; i++) {
	                msg += args[i] + " ";
	            }

				msg = msg.substring(0, msg.length() - 1);
				
				if (!sender.isOp() || !sender.hasPermission("togglepm.bypass")) {
				if (Lists.togglepm.contains(target.getName())) {
					sender.sendMessage(ChatColor.GRAY + "You can not message " + Red + "'" + target.getName() + "'" + Gray + " while his messsages are disabled.");
					return true;
				}
			}
				
				if (target == sender) {
					sender.sendMessage(Gray + "<" + sender.getName() + " -> " + target.getName() + "> " + Red + msg);
					
					Lists.reply.put(sender.getName(), target.getName());
					Lists.reply.put(target.getName(), sender.getName());
					return true;
				}
				if (sender.isOp() || Utils.isVip(sender)) {
					sender.sendMessage(Gray + "<" + sender.getName() + " -> " + target.getName() + "> " + Red + msg);
					target.sendMessage(Gray + "<" + sender.getName() + " -> " + target.getName() + "> " + Red + msg);
					
					Lists.reply.put(sender.getName(), target.getName());
					Lists.reply.put(target.getName(), sender.getName());
					return true;
				}
				
				
				sender.sendMessage(Gray + "<" + sender.getName() + " -> " + target.getName() + "> " + msg);
				target.sendMessage(Gray + "<" + sender.getName() + " -> " + target.getName() + "> " + msg);
				
				Lists.reply.put(sender.getName(), target.getName());
				Lists.reply.put(target.getName(), sender.getName());
				return true;
			}
				return true;
			} catch (Exception ex) {
				invalidArguments();
				msg(Gray + "/msg <player> <message>");
			}
			return true;
	}	
}
