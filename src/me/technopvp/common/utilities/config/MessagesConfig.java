package me.technopvp.common.utilities.config;

import java.io.File;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.CommonCore;

import org.bukkit.configuration.file.FileConfiguration;

public class MessagesConfig extends CommonCore {
	static dCommon plugin = dCommon.instance;

	public static void generateMessagesConfig() {
		/**
		 * Getting messages.yml
		 */
		if(!new File(plugin.getDataFolder(), "messages.yml").exists()) {
			plugin.saveResource("messages.yml", false);
		}
		plugin.messages = ConfigUtils.getConfig("messages.yml");
	}

	/**
	 * Grab the messages file, and get returns the configeration file.
	 *
	 * @return messages Yamal file.
	 */

	public static FileConfiguration getMessagesConfig() {
		return plugin.messages;
	}

	/**
	 * Returns the string, if it existes.
	 *
	 * @param string - The string for the path.
	 *
	 * @return the string.
	 */

	public static String getString(String string) {
		return getMessagesConfig().getString(string);
	}
}
