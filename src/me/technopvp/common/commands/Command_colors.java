package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.CommonCore;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_colors extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		player.sendMessage(CommonCore.Gold + "Minecraft Color Codes");
		player.sendMessage(CommonCore.Black + "&0" + CommonCore.Reset + " | " + CommonCore.Black + "This is Black");
		player.sendMessage(CommonCore.DarkBlue + "&1" + CommonCore.Reset + " | " + CommonCore.DarkBlue + "This is Dark Blue");
		player.sendMessage(CommonCore.DarkGreen + "&2" + CommonCore.Reset + " | " + CommonCore.DarkGreen + "This is Dark Green");
		player.sendMessage(CommonCore.DarkAqua + "&3" + CommonCore.Reset + " | " + CommonCore.DarkAqua + "This is Dark Aqua");
		player.sendMessage(CommonCore.DarkRed + "&4" + CommonCore.Reset + " | " + CommonCore.DarkRed + "This is Dark Red");
		player.sendMessage(CommonCore.Purple + "&5" + CommonCore.Reset + " | " + CommonCore.Purple + "This is Purple");
		player.sendMessage(CommonCore.Gold + "&6" + CommonCore.Reset + " | " + CommonCore.Gold + "This is Gold");
		player.sendMessage(CommonCore.DarkGray + "&8" + CommonCore.Reset + " | " + CommonCore.DarkGray + "This is DarkGray");
		player.sendMessage(CommonCore.Gray + "&7" + CommonCore.Reset + " | " + CommonCore.Gray + "This is Gray");
		player.sendMessage(CommonCore.Blue + "&9" + CommonCore.Reset + " | " + CommonCore.Blue + "This is Blue");
		player.sendMessage(CommonCore.Green + "&a" + CommonCore.Reset + " | " + CommonCore.Green + "This is Green");
		player.sendMessage(CommonCore.Aqua + "&b" + CommonCore.Reset + " | " + CommonCore.Aqua + "This is Aqua");
		player.sendMessage(CommonCore.Red + "&c" + CommonCore.Reset + " | " + CommonCore.Red + "This is Red");
		player.sendMessage(CommonCore.Pink + "&d" + CommonCore.Reset + " | " + CommonCore.Pink + "This is Pink");
		player.sendMessage(CommonCore.Yellow + "&e" + CommonCore.Reset + " | " + CommonCore.Yellow + "This is Yellow");
		player.sendMessage(CommonCore.White + "&f" + CommonCore.Reset + " | " + CommonCore.White + "This is White");
		return true;
}
}
