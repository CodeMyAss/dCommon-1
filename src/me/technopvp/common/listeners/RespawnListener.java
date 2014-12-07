package me.technopvp.common.listeners;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.config.SpawnConfig;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {
	dCommon plugin = dCommon.instance;

	@EventHandler
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		for (String configs : SpawnConfig.getInstance().getSpawnConfig().getConfigurationSection("spawns").getKeys(false)) {
		if (!configs.contains(plugin.permission.getPrimaryGroup(player))) {
			event.setRespawnLocation(SpawnConfig.getInstance().getDefaultSpawnLocation());
		}else {
			event.setRespawnLocation(SpawnConfig.getInstance().getGroupSpawn(player));
		}
		}
	}
}
