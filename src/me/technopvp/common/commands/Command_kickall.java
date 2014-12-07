package me.technopvp.common.commands;

import me.technopvp.common.utilities.Utils;
import me.technopvp.common.utilities.enums.Level;
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
@Permissions(Permission.OWNER)
public class Command_kickall extends CommonCommand {

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;

		if (args.length == 0) {
			for (Player allPlayers : Bukkit.getOnlinePlayers()) {
				if (allPlayers != player) {
					allPlayers.kickPlayer(Red + "The server has been kicked from the game.");
				}
			}
			player.sendMessage(Red + "Kicked all players from the server.");
			Utils.log(Level.FATAL, "The server has been kicked by " + player.getName());
			return true;
		}
		if (args.length == 1) {
			String msg = "";
			for (String arg : args) {
				msg = msg + arg + " ";
			}

			msg = msg.substring(0, msg.length() - 1);
			msg = msg.replaceAll("&", "§");
			
			for (Player allPlayers : Bukkit.getOnlinePlayers()) {
				if (allPlayers != player) {
					allPlayers.kickPlayer("" + Red + ChatColor.UNDERLINE + "The Server Has Been Kicked.\n\n" 
				+ Gray + "Reason: " + Red + Bold + msg + "\n" 
				+ Gray + "By: " + Red + Bold + player.getName());
				}
			}
			player.sendMessage(Red + "Kicked all players from the server.");
			Utils.log(Level.FATAL, "The server has been kicked by " + player.getName() + " for " + msg);
			return true; 
		}
		return true;
	}

}
