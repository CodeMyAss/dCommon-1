package me.technopvp.common.utility.config;

import java.io.File;
import java.io.IOException;

import me.technopvp.common.dCommon;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtils {
	static dCommon plugin = dCommon.instance;

	public static FileConfiguration getConfig(FileConfiguration config) {
		return config;
	}
	
	public static FileConfiguration getConfig(String string) {
		return YamlConfiguration.loadConfiguration(getFile(string));
	}

	public static File getFile(String string) {
		return new File(plugin.getDataFolder(), string);
	}
	
	public static void delete(File file) {
		file.delete();
	}
	
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