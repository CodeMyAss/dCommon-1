package me.technopvp.common.managers;

import me.technopvp.common.dCommon;
import me.technopvp.common.api.BarApi;
import me.technopvp.common.api.GhostManager;
import me.technopvp.common.bridge.VaultBridge;
import me.technopvp.common.listeners.ChatListener;
import me.technopvp.common.listeners.JoinListener;
import me.technopvp.common.listeners.LeaveListener;
import me.technopvp.common.listeners.LoginListener;
import me.technopvp.common.listeners.PlayerListener;
import me.technopvp.common.listeners.RespawnListener;
import me.technopvp.common.utility.config.MOTDConfig;
import me.technopvp.common.utility.config.MessagesConfig;
import me.technopvp.common.utility.config.SpawnConfig;
import me.technopvp.common.utility.player.UserManager;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;

public class InitializeManager {
	dCommon plugin = dCommon.instance;

	public InitializeManager() {
		plugin.ghostManager = new GhostManager(plugin);

		loadAnnouncements();
		clearGarbage();
		getEvents();
		setupVault();
		setUpConfigerationFiles();
	}

	public void setUpConfigerationFiles() {
		/* Genrates the 'playerdata'(users) folder. */
		plugin.user = new UserManager(plugin);

		/* Generates the spawn folder */
		SpawnConfig.getInstance().generateSpawnConfig();

		/* Get's the message.yml file */
		MessagesConfig.generateMessagesConfig();

		/* Generates the MOTD.text file */
		MOTDConfig.getInstance().generateMOTDConfig();

		/* Creates/saves config.yml file. */
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveDefaultConfig();
	}

	public void getEvents() {
		plugin.pluginmanager = plugin.getServer().getPluginManager();

		plugin.pluginmanager.registerEvents(new RespawnListener(), plugin);
		plugin.pluginmanager.registerEvents(new PlayerListener(), plugin);
		plugin.pluginmanager.registerEvents(new LoginListener(), plugin);
		plugin.pluginmanager.registerEvents(new BarApi(), plugin);
		plugin.pluginmanager.registerEvents(new JoinListener(), plugin);
		plugin.pluginmanager.registerEvents(new LeaveListener(), plugin);
		plugin.pluginmanager.registerEvents(new ChatListener(), plugin);
	}

	public void loadAnnouncements() {
		AnnouncerManager.startAnnouncements();
	}

	public void clearGarbage() {
		/* Clear all mid-exploding tnt */
		for (World world : Bukkit.getServer().getWorlds()) {
			for (Entity entity : world.getEntities()) {
				if ((entity instanceof TNTPrimed)) {
					entity.remove();
				}
			}
		}

	}

	public void setupVault() {
		VaultBridge.getInstance().setupAll(true, true, true);
	}
}
