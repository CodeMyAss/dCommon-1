package me.technopvp.common.commands;

import me.technopvp.common.dCommon;
import me.technopvp.common.api.GoogleShortener;
import me.technopvp.common.managers.MessageManager;
import me.technopvp.common.utilities.enums.Level;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Permissions.Permission;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;

@Source(SourceType.ANY)
@Permissions(Permission.ANYONE)
public class Command_google extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;

		if (!player.hasPermission("google.yes")) {
			noPermission();
			return true;
		}
		if (args.length > 0) {
			String searchTerms = Joiner.on(" ").join(args);
			String url = "https://www.google.com/search?q=" + searchTerms.replace("-c", "").trim();
				try {
					GoogleShortener gs = new GoogleShortener(url);
					if (!searchTerms.contains("-c")) {
						sender.sendMessage("§3" + gs.shorten());
					} else if ((sender instanceof Player)) {
						player.chat(gs.shorten());
					} else {
						sender.sendMessage("You can only use the '-c' flag as a player!");
					}
				} catch (Exception e) {
					MessageManager.log(Level.HIGH, "Encountered a high error with /google");
					e.printStackTrace();
				}
		} else {
			showUsage(cmd);
		}
		return true;
}
}
