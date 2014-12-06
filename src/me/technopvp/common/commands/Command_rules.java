package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_rules extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		player.sendMessage(Red + "If Any of these rules are broken it could result in a ban, mute or kick");					
		player.sendMessage(Gray + "1.) No spamming");
		player.sendMessage(Gray + "2.) No hacking of any kind");
		player.sendMessage(Gray + "3.) No xraying");
		player.sendMessage(Gray + "4.) No advertising");
		player.sendMessage(Gray + "5.) Please be kind");
		player.sendMessage(Gray + "6.) Swearing is allowed");
		player.sendMessage(Gray + "8.) DDoS comedy is not allowed");
		player.sendMessage(Gray + "9.) No abusing bugs/glitches");
		player.sendMessage(Gray + "10.) Use common sense");
		return true;
	}

}
