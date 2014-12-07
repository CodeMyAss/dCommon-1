package me.technopvp.common.utilities.config;

import java.io.File;
import java.io.IOException;

import me.technopvp.common.dCommon;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtils {
	static dCommon plugin = dCommon.instance;

	/**
	 * Grab a configeration file by a string.
	 *
	 * @param string
	 *            - The configeration file path you are getting.
	 *
	 * @return file - The configeration file that was grabbed.
	 *
	 * @see ConfigUtils#getConfig(String)
	 */

	public static FileConfiguration getConfig(String string) {
		return YamlConfiguration.loadConfiguration(getFile(string));
	}

	/**
	 * Get a file in the data foler, by a string.
	 *
	 * @param string
	 *            - The file name.
	 * @return the file.
	 *
	 * @see ConfigUtils#getFile(String)
	 */

	public static File getFile(String string) {
		return new File(plugin.getDataFolder(), string);
	}

	/**
	 * Deletes a file.
	 *
	 * @param file
	 *            - The file you are a deleting.
	 */

	public static void delete(File file) {
		file.delete();
	}

	/**
	 * @param string
	 *            - The string of the file.
	 * @param path
	 *            - The string of the path.
	 * @param value
	 *            The string of the value, you are setting.
	 *
	 * @see ConfigUtils#set(String, String, String)
	 *
	 * @throws IOException - Triggered when the file can't be set, or saved.
	 */

	public static void set(String string, String path, String value) {
		File file = getFile(string);
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		config.set(path, value);
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}