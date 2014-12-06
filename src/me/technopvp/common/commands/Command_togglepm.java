package me.technopvp.common.commands;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.utility.player.PlayerUtils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_togglepm extends CommonCommand {
	dCommon plugin = dCommon.instance;

	private String disableMessages = Gray + "You have " + Red + "disabled " + Gray + "messaging, you will no longer recive messages.";
	private String enableMessages = Gray + "You have " + Red + "enabled " + Gray + "messaging. You can now recive messages, and pms.";
	
	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		if (args.length == 0) {
			if (!Lists.togglepm.contains(player.getName())) {
			/* Disable the players ablitty to be messaged */	
			Lists.togglepm.add(player.getName());
			msg(disableMessages);
			
			/* Removes them, and target from current conversation */
			Player target = Bukkit.getPlayer(Lists.reply.get(player.getName()));
			
			PlayerUtils.removeFromCurrentConversation(player);
			PlayerUtils.removeFromCurrentConversation(target);
		}else {
			/* Allows the player to be messages again */
			
			msg(enableMessages);
			Lists.togglepm.remove(player.getName());
			return true;
		}
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("true")) {
				/* Disable the players ablitty to be messaged */
				if (Lists.togglepm.contains(player.getName())) {
					invalidUsage();
					msg(Gray + "Your messages/private messages are alredy disabled.");
					return true;
				}
				Player target = Bukkit.getPlayer(Lists.reply.get(player.getName()));
				
				PlayerUtils.disableMessages(player);
				
				/* Removes them, and target from current conversation */
				PlayerUtils.removeFromCurrentConversation(player);
				PlayerUtils.removeFromCurrentConversation(target);
				
				player.sendMessage(disableMessages);
				return true;
			}
			if (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("false")) {
				/* Allows the players to be messaged */
				
				if (!Lists.togglepm.contains(player.getName())) {
					invalidUsage();
					msg(Gray + "Your messages/private messages are alredy enabled.");
					return true;
				}
				PlayerUtils.enableMessages(player);
				player.sendMessage(enableMessages);
				return true;
			}
			if (args[0].equalsIgnoreCase("toggle")) {
				/* Disable the players ablility to be messaged */
				
				if (!Lists.togglepm.contains(player.getName())) {
					Lists.togglepm.add(player.getName());
					msg(disableMessages);
					
					/* Removes them, and target from current conversation */
					Player target = Bukkit.getPlayer(Lists.reply.get(player.getName()));
					
					PlayerUtils.removeFromCurrentConversation(player);
					PlayerUtils.removeFromCurrentConversation(target);
				}else {
					/* Allows the players to be messaged */
					
					Lists.togglepm.remove(player.getName());
					msg(enableMessages);
					return true;
				}
				return true;
			}
			if (player.hasPermission("togglepm.other")) {
				Player target = Bukkit.getPlayer(args[0]);
				
				if (target == null) {
					targetOffline(args[0]);
					return true;
				}
				if (!Lists.togglepm.contains(target.getName())) {
				/* Disable the targets ablility to be messaged */
					
				msg(Gray + "You have disabled messing for " + Red + target.getName() + Gray + ".");
				target.sendMessage(disableMessages);
				Lists.togglepm.add(target.getName());
				
				/* Removes them, and target from current conversation */
				Player targetMessaged = Bukkit.getPlayer(Lists.reply.get(target.getName()));
				
				PlayerUtils.removeFromCurrentConversation(player);
				PlayerUtils.removeFromCurrentConversation(targetMessaged);
				}else {
					/* Allows the target to be messages */
					
					msg(Gray + "You have enabled messages for " + Red + target.getName() + Gray + ".");
					
					target.sendMessage(enableMessages);
					Lists.togglepm.remove(target.getName());
					return true;
				}
			}else {
				noPermission();
			}
			return true;
		}	
		return true;
}
}
