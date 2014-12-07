package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.OWNER)
public class Command_test extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, final String[] args) {
		Player player = (Player)sender;
		try {
			player.wait(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}