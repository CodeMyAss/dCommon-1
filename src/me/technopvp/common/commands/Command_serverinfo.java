package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Permissions.Permission;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.utility.Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_serverinfo extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		String Email = plugin.getConfig().getString("email");
		Email = Email.replaceAll("&", "§");
        Email = Email.replaceAll("%player", player.getName());
		player.sendMessage(ChatColor.GRAY + "Email: " + Blue + Email );
		String Website = plugin.getConfig().getString("website");
		Website = Website.replaceAll("&", "§");
		Website = Website.replaceAll("%player", player.getName());
		player.sendMessage(Gray + "Website: " + Blue + Website);
		player.sendMessage(ChatColor.GRAY + "Donation Page: " + Blue + Utils.dPage());
		String Account = plugin.getConfig().getString("account");
		Account = Account.replaceAll("&", "§");
		Account = Account.replaceAll("%player", player.getName());
		player.sendMessage(ChatColor.GRAY + "Your Account: " + Blue + Account);
		return true;
}
}
