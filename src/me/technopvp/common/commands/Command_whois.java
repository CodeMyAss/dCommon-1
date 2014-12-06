package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.api.FancyMessage;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.utility.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.OWNER)
public class Command_whois extends CommonCommand {
	dCommon plugin = dCommon.instance;

	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
	
	
	if (cmd.getName().equalsIgnoreCase("whois")) {
		if (!player.hasPermission("whois.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			showUsage(cmd);
			return true;
		}
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			Utils.targetOffline(player, args[0]);
			return true;
		}
		player.sendMessage(Gray + "----- " + Red + target.getName() + Gray + " -----" );
		player.sendMessage(Gray + "Name: " + target.getDisplayName());
		new FancyMessage(Gray + "Health: " + Red + player.getHealth() / 2 + Gray + "/" + Red + player.getMaxHealth() / 2).tooltip(Gray + "Click here to heal " + target.getName()).command("/heal " + target.getName()).send(player);
		new FancyMessage(Gray + "Hunger: " + Red + player.getFoodLevel() / 2 + Gray + "/" + Red + "10").tooltip(Gray + "Click here to feed " + target.getName()).command("/feed " + target.getName()).send(player);
		new FancyMessage(Gray + "Xp: " + Red + target.getTotalExperience() + Gray + " (" + Red  + "Level " + player.getLevel() + Gray + ")")
		.tooltip(Red + "" + Gray + Bold + "Exp Level: " + Red + Bold + Math.round(player.getExp() * 100) + Red + "% Complete.")
		.send(player);
		new FancyMessage(Gray + "Location: (" + Red 
				+ target.getWorld().getName().toString().toLowerCase() + Gray + ", " + Red 
				+ target.getLocation().getBlockX() + Gray + ", " + Red 
				+ target.getLocation().getBlockY() + Gray + ", " + Red + 
				target.getLocation().getBlockZ() + Gray + ")").tooltip(Red + "" + Red + target.getName() + Red + " is " + Bold + Math.round(player.getLocation().distance(target.getLocation())) + Red + " blocks away from you.").send(player);
		player.sendMessage(Gray + "Ip: " + Red + (Utils.isVip(target) ? "Blocked" : target.getAddress().getHostName()));
		player.sendMessage(ChatColor.GRAY + "Op: " + Red + Bold + (player.isOp() ? "TRUE" : "false"));
		player.sendMessage(ChatColor.GRAY + "Gamemode: " + Red + (player.getGameMode().equals(GameMode.CREATIVE) ? Bold + target.getGameMode().toString().toUpperCase() : target.getGameMode().toString().toUpperCase()));
		if (target.getAllowFlight() == true) {
			new FancyMessage(Gray + "Flying: " + Red + "true").tooltip(Gray + "Click to set " + target.getName() + " to flying.").command("/fly " + target.getName()).send(player);
		}else {
			new FancyMessage(Gray + "Flying: " + Red + "false").tooltip(Gray + "Click to remove " + target.getName() + " from flying.").command("/fly " + target.getName()).send(player);
			return true;
		}
		
	}
	return true;
	}
}
