package me.technopvp.common.utility.player;

import me.technopvp.common.dCommon;
import me.technopvp.common.utility.player.Ban.BanInfo;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class User {
	static dCommon plugin = dCommon.instance;

	String user;
	UserConfig playerConfig;

	protected User(dCommon plugin, String user) {
		this.user = user;
		this.playerConfig = new UserConfig(plugin, user);
	}

	public UserConfig getPlayerConfig() {
		return this.playerConfig;
	}

	/**
	 * Grab the user, and the user settings.
	 * 
	 * @param user
	 *            - Gets the user.
	 * @return The user options.
	 */

	public static User getUser(String user) {
		return plugin.user.getUser(user);
	}
	
	public static FileConfiguration getUserConfig(String user) {
		return plugin.user.getUser(user).getPlayerConfig().getConfig();
	}

	/* Setters, and getters for Name */
	public static void setName(String user, String name) {
		getUser(user).getPlayerConfig().getConfig().set("Name", name);
		getUser(user).getPlayerConfig().savePlayerConfig();
	}

	public static String getName(String user) {
		return getUser(user).getPlayerConfig().getConfig().getString("Name");
	}

	/* Setters, and getters for DisplayName */
	public static void setDisplayName(String user, String displayname) {
		getUser(user).getPlayerConfig().getConfig()
				.set("DisplayName", displayname);
		getUser(user).getPlayerConfig().savePlayerConfig();
	}

	public static String getDisplayName(String user) {
		return getUser(user).getPlayerConfig().getConfig()
				.getString("DisplayName");
	}

	/* Setters, and getters for IP */
	public static void setIP(String user, String address) {
		getUser(user).getPlayerConfig().getConfig().set("IP", address);
		getUser(user).getPlayerConfig().savePlayerConfig();
	}

	public static String getIP(String user) {
		return getUser(user).getPlayerConfig().getConfig().getString("IP");
	}

	public static void setBanned(String user, boolean ban) {
		if (ban == false) {
			getUser(user).getPlayerConfig().getConfig().set("Banned", false);
		} else {
			getUser(user).getPlayerConfig().getConfig().set("Banned", true);
		}
	}

	public static String getBanInformation(String user, BanInfo type) {
		switch (type) {
		case REASON:
			return User.getUser(user).getPlayerConfig().getConfig().getString("Reason");
		case BY:
			return User.getUser(user).getPlayerConfig().getConfig().getString("BannedBy");
		default:
			return null;
		}
	}
	
	public static int getWarnedLevel(String user) {
		return getUserConfig(user).getInt("Warn.Warnings");
	}
	public static void warn(String user, int level, String reason, String by) {
		getUserConfig(user).set("Warn.Warnings", level);
		getUserConfig(user).set("Warn.Reason", reason);
		getUserConfig(user).set("Warn.By", by);
		getUser(user).getPlayerConfig().savePlayerConfig();
	}

		
	public static boolean isBanned(String user) {
		return (User.getUser(user).getPlayerConfig().getConfig().getBoolean("Banned"));
	}

	public static void loadUserData(Player user) {
		setName(user.getName(), user.getName());
		setBanned(user.getName(), false);
		setIP(user.getName(), user.getAddress().getHostName());
	}

}
