

package me.technopvp.common.utilities.player;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.CommonCore;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Ban extends CommonCore {

	dCommon plugin = dCommon.instance;

	private OfflinePlayer player;
	private String reason, bannedby;

	/**
	 * Ban a player, for a reason, and who there banned by.
	 *
	 * @param name
	 *            The name of the player you are banning.
	 * @param reason
	 *            The reason this player is being banned.
	 * @param bannedby
	 *            Who the player is being banmned by.
	 *
	 */
	@SuppressWarnings("deprecation")
	public Ban(OfflinePlayer player, String reason, String bannedby) {
		this.reason = reason;
		this.bannedby = bannedby;
		this.player = player;

		User.getUser(player.getName()).getPlayerConfig().getConfig().set("Ban.Banned", true);
		User.getUser(player.getName()).getPlayerConfig().getConfig().set("Ban.Reason", reason.toString().trim());
		User.getUser(player.getName()).getPlayerConfig().getConfig().set("Ban.BannedBy", bannedby);
		User.getUser(player.getName()).getPlayerConfig().savePlayerConfig();

		for (Player all : Bukkit.getOnlinePlayers()) {
			all.sendMessage(Green + player.getName() + " has been banned by " + bannedby + (all.hasPermission("ban.yes") || all.isOp()
					? (reason == "Unspecified" ? (all.isOp()
							? " for Unspecified." : ".")
							: " for " + reason.trim() + ".") : "."));
		}
		player.setBanned(true);
	}

	/**
	 * Check if the player is banned, if so return true, else retrun false.
	 *
	 * @param player
	 *            The player you are checking.
	 *
	 */

	public static Boolean isBanned(String player) {
		return (User.getUser(player).getPlayerConfig().getConfig().getBoolean("Ban.Banned"));
	}

	public static String getReason(String user) {
		return User.getUser(user).getPlayerConfig().getConfig().getString("Ban.Reason");
	}

	public String getReason() {
		return reason;
	}

	public static String getBannedBy(String user) {
		return User.getUser(user).getPlayerConfig().getConfig().getString("Ban.BannedBy");
	}

	public String getWhoBanned() {
		return bannedby;
	}

	public OfflinePlayer getPlayer() {
		return player;
	}

	public enum BanInfo {
		REASON,
		BY;
	}

}
