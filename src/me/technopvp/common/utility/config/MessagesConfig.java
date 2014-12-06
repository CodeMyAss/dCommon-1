package me.technopvp.common.utility.config;

import java.io.File;

import me.technopvp.common.dCommon;
import me.technopvp.common.utility.CommonCore;

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
	
	public static FileConfiguration getMessagesConfig() {
		return plugin.messages;
	}
	
	public static String getString(String string) {
		return getMessagesConfig().getString(string);
	}
}
