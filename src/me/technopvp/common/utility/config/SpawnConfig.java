package me.technopvp.common.utility.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import me.technopvp.common.dCommon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SpawnConfig {
    dCommon plugin = dCommon.instance;

    private SpawnConfig() { }

    static SpawnConfig instance = new SpawnConfig();

    public static SpawnConfig getInstance() {
            return instance;
    }

    private FileConfiguration spawnConfig = null;
    private File spawnConfigFile = null;

    public void generateSpawnConfig() {
        if (spawnConfigFile != null) {
            spawnConfigFile.delete();
        }
        reloadSpawnConfig();
    }

    public void reloadSpawnConfig() {
        if (spawnConfigFile == null) {
            spawnConfigFile = new File(plugin.getDataFolder(), "spawn.yml");
        }
        spawnConfig = YamlConfiguration.loadConfiguration(spawnConfigFile);

        InputStream defConfigStream = plugin.getResource("spawn.yml");
        if (defConfigStream != null) {
            @SuppressWarnings("deprecation")
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            spawnConfig.setDefaults(defConfig);
        }
    }

    public FileConfiguration getSpawnConfig() {
        if (spawnConfig == null) {
            reloadSpawnConfig();
        }
        return spawnConfig;
    }

    public void saveSpawnConfig() {
        if (spawnConfig == null || spawnConfigFile == null) {
            return;
        }
        try {
            spawnConfig.save(spawnConfigFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save the warps config to the disk!");
        }
    }

    public Location getDefaultSpawnLocation() {
    	Location defaultSpawnLocation = new Location(Bukkit.getWorld(SpawnConfig.getInstance().getSpawnConfig().getString("spawns.default.world")),
				SpawnConfig.getInstance().getSpawnConfig().getDouble("spawns.default.x"),
				SpawnConfig.getInstance().getSpawnConfig().getDouble("spawns.default.y"),
				SpawnConfig.getInstance().getSpawnConfig().getDouble("spawns.default.z"),
				SpawnConfig.getInstance().getSpawnConfig().getInt("spawns.default.yaw"),
				SpawnConfig.getInstance().getSpawnConfig().getInt("spawns.default.pitch"));

    	return defaultSpawnLocation;
    }

    public Location getGroupSpawn(Player player) {
		Location playerGroupSpawnLocation = new Location(Bukkit.getWorld(SpawnConfig.getInstance().getSpawnConfig().getString("spawns.default.world")),
				SpawnConfig.getInstance().getSpawnConfig().getDouble("spawns." + plugin.permission.getPrimaryGroup(player) + ".x"),
				SpawnConfig.getInstance().getSpawnConfig().getDouble("spawns." + plugin.permission.getPrimaryGroup(player) + ".y"),
				SpawnConfig.getInstance().getSpawnConfig().getDouble("spawns." + plugin.permission.getPrimaryGroup(player) + ".z"),
				SpawnConfig.getInstance().getSpawnConfig().getInt("spawns." + plugin.permission.getPrimaryGroup(player) + ".yaw"),
				SpawnConfig.getInstance().getSpawnConfig().getInt("spawns." + plugin.permission.getPrimaryGroup(player) + ".pitch"));

    	return playerGroupSpawnLocation;
    }

    public void setSpawn(String spawn, String world, double x, double y, double z, float pitch, float yaw) {
    	SpawnConfig.getInstance().getSpawnConfig().set("spawns." + spawn + ".world", world);
    	SpawnConfig.getInstance().getSpawnConfig().set("spawns." + spawn + ".x", x);
    	SpawnConfig.getInstance().getSpawnConfig().set("spawns." + spawn + ".y", y);
    	SpawnConfig.getInstance().getSpawnConfig().set("spawns." + spawn + ".z", z);
    	SpawnConfig.getInstance().getSpawnConfig().set("spawns." + spawn + ".pitch", pitch);
    	SpawnConfig.getInstance().getSpawnConfig().set("spawns." + spawn + ".yaw", yaw);
    	saveSpawnConfig();
    }
}