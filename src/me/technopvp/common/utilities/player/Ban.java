

package me.technopvp.common.utilities.player;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.CommonCore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Ban extends CommonCore {

	dCommon plugin = dCommon.instance;

	private String name, reason, bannedby;

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
	public Ban(String name, String reason, String bannedby) {
		this.reason = reason;
		this.bannedby = bannedby;
		this.name = name;

		User.getUser(name).getPlayerConfig().getConfig().set("Ban.Banned", true);
		User.getUser(name).getPlayerConfig().getConfig().set("Ban.Reason", reason.toString().trim());
		User.getUser(name).getPlayerConfig().getConfig().set("Ban.BannedBy", bannedby);
		User.getUser(name).getPlayerConfig().savePlayerConfig();

		for (Player all : Bukkit.getOnlinePlayers()) {
			all.sendMessage(Green + name + " has been banned by " + bannedby + (all.hasPermission("ban.yes") || all.isOp()
					? (reason == "Unspecified" ? (all.isOp()
							? " for Unspecified." : ".")
							: " for " + reason.trim() + ".") : "."));
		}
	}

	/**
	 * Check if the player is banned, if so return true, else retrun false.
	 *
	 * @param player
	 *            The player you are checking.
	 *
	 */
	public static Boolean isBanned(String player) {
		if (User.getUser(player).getPlayerConfig().getConfig().getBoolean("Ban.Banned") == true) {
			return true;
		} else {
			return false;
		}
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getWhoBanned() {
		return bannedby;
	}

	public void setWhoBanned(String bannedby) {
		this.bannedby = bannedby;
	}

	public String getPlayer() {
		return name;
	}

	public enum BanInfo {
		REASON,
		BY;
	}

}
