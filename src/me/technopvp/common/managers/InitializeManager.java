package me.technopvp.common.managers;

import me.technopvp.common.dCommon;
import me.technopvp.common.api.GhostManager;
import me.technopvp.common.api.barapi.BarApi;
import me.technopvp.common.integration.VaultBridge;
import me.technopvp.common.listeners.ChatListener;
import me.technopvp.common.listeners.JoinListener;
import me.technopvp.common.listeners.LeaveListener;
import me.technopvp.common.listeners.LoginListener;
import me.technopvp.common.listeners.PlayerListener;
import me.technopvp.common.listeners.RespawnListener;
import me.technopvp.common.utilities.config.MOTDConfig;
import me.technopvp.common.utilities.config.MessagesConfig;
import me.technopvp.common.utilities.config.SpawnConfig;
import me.technopvp.common.utilities.player.UserManager;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;

public class InitializeManager {
	dCommon plugin = dCommon.instance;

	public InitializeManager() {
		plugin.ghostManager = new GhostManager(plugin);

		registerEvents();
		setUpConfigerationFiles();
		loadAnnouncements();
		setupVault();
		clearGarbage();
	}

	/**
	 * Set's up the configeration files.
	 *
	 * @see InitializeManager#setUpConfigerationFiles()
	 */

	public void setUpConfigerationFiles() {
		/* Genrates the 'playerdata'(users) folder. */
		plugin.user = new UserManager(plugin);

		/* Generates the spawn folder's */
		SpawnConfig.getInstance().generateSpawnConfig();

		/* Get's the message.yml file */
		MessagesConfig.generateMessagesConfig();

		/* Generates the MOTD.text file */
		MOTDConfig.getInstance().generateMOTDConfig();

		/* Creates/saves config.yml file. */
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveDefaultConfig();
	}

	/**
	 * Registers all the listeners, and events.
	 *
	 * @see InitializeManager#registerEvents()
	 */

	public void registerEvents() {
		plugin.pluginmanager = plugin.getServer().getPluginManager();

		plugin.pluginmanager.registerEvents(new RespawnListener(), plugin);
		plugin.pluginmanager.registerEvents(new PlayerListener(), plugin);
		plugin.pluginmanager.registerEvents(new LoginListener(), plugin);
		plugin.pluginmanager.registerEvents(new BarApi(), plugin);
		plugin.pluginmanager.registerEvents(new JoinListener(), plugin);
		plugin.pluginmanager.registerEvents(new LeaveListener(), plugin);
		plugin.pluginmanager.registerEvents(new ChatListener(), plugin);
	}

	/**
	 * Loags all the anouncements from the <br>
	 * announcer class.
	 *
	 * @see InitializeManager#loadAnnouncements()
	 */

	public void loadAnnouncements() {
		AnnouncerManager.startAnnouncements();
	}

	/**
	 * Clears all un-necessary entites.
	 *
	 * @see InitializeManager#clearGarbage()
	 */

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

	/**
	 * Set up vault integration.
	 *
	 * @see InitializeManager#setupVault()
	 */

	public void setupVault() {
		VaultBridge.getInstance().setupAll(true, true, true);
	}
}
