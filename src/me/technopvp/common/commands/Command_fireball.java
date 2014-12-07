package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;

@Source(SourceType.ANY)
@Permissions(Permission.OWNER)
public class Command_fireball extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if (!player.hasPermission("fireball.yes")) {
			noPermission();
			return true;
		}
		if (args.length == 0) {
			player.launchProjectile(Fireball.class);
			return true;
		}
		if (args[0].equalsIgnoreCase("wither")) {
			player.launchProjectile(WitherSkull.class);
			return true;
		}
		if (args[0].equalsIgnoreCase("fireball")) {
			player.launchProjectile(Fireball.class);
			return true;
		}
		return true;
	}

}
