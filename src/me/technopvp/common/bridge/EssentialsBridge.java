package me.technopvp.common.bridge;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Level;
import me.technopvp.common.utility.Utils;

import org.anjocaido.groupmanager.GroupManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;

public class EssentialsBridge {
	static dCommon plugin = dCommon.instance;

	private static Essentials essentialsPlugin = null;
	private static GroupManager groupManagerPlugin = null;

	private EssentialsBridge() {
		throw new AssertionError();
	}

	public static Essentials getEssentialsPlugin() {
		if (essentialsPlugin == null) {
			try {
				final Plugin essentials = Bukkit.getServer().getPluginManager()
						.getPlugin("Essentials");
				if (essentials != null) {
					if (essentials instanceof Essentials) {
						essentialsPlugin = (Essentials) essentials;
					}
				}
			} catch (Exception ex) {
				Utils.log(Level.MEDIUM, "" + ex);
			}
		}
		return essentialsPlugin;
	}
	
	public static GroupManager getGroupManagerPlugin() {
		if (groupManagerPlugin == null) {
			try {
				final Plugin groupmanager = Bukkit.getServer().getPluginManager()
						.getPlugin("GroupManager");
				if (groupmanager != null) {
					if (groupmanager instanceof GroupManager) {
						groupManagerPlugin = (GroupManager) groupmanager;
					}
				}
			} catch (Exception ex) {
				Utils.log(Level.MEDIUM, "" + ex);
			}
		}
		return groupManagerPlugin;
	}

	public static User getUser(String username) {
		try {
			final Essentials essentials = getEssentialsPlugin();
			if (essentials != null) {
				return essentials.getUserMap().getUser(username);
			}
		} catch (Exception ex) {
			Utils.log(Level.MEDIUM, "" + ex);
		}
		return null;
	}

	public static void setNickname(String username, String nickname) {
		try {
			final User user = getUser(username);
			if (user != null) {
				user.setNickname(nickname);
				user.setDisplayNick();
			}
		} catch (Exception ex) {
			Utils.log(Level.MEDIUM, "" + ex);
		}
	}

	public static boolean isEssentialsEnabled() {
		try {
			final Essentials essentials = getEssentialsPlugin();
			if (essentials != null) {
				return essentials.isEnabled();
			}
		} catch (Exception ex) {
			Utils.log(Level.MEDIUM, "" + ex);
		}
		return false;
	}
}