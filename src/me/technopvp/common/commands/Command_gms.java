package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.ADMIN)
public class Command_gms extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		if (!player.hasPermission("gms.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			player.setGameMode(GameMode.SURVIVAL);
			player.sendMessage(Gray + "Your gamemode has been set to " + Red + Bold + player.getGameMode().toString().toLowerCase() + Gray + ".");
			return true;
		}
		if (args.length == 1) {
		Player t = Bukkit.getPlayer(args[0]);
		if (t == player) {
			player.setGameMode(GameMode.SURVIVAL);
			player.sendMessage(Gray + "Your gamemode has been set to " + Red + Bold + player.getGameMode().toString().toLowerCase() + Gray + ".");
			return true;
		}
		if (t == null) {
			player.sendMessage(Red + "Can't find player " + "'" + args[0] + "'" + ".");
            return true;			
		}
		t.setGameMode(GameMode.SURVIVAL);
		player.sendMessage(Gray + "You have set " + Red + Bold + t.getName() + "'s " + Gray + "gamemode to " + Red + Bold +player.getGameMode().toString().toLowerCase() + Gray + ".");
		t.sendMessage(Gray + "Your gamemode has been set to " + Red + Bold + player.getGameMode().toString().toLowerCase() + Gray + ".");
		return true;
	}
	return true;
}
}
