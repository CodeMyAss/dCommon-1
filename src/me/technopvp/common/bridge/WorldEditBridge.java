package me.technopvp.common.bridge;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class WorldEditBridge {
	static dCommon plugin = dCommon.instance;

	private static WorldEditPlugin worldEditPlugin = null;

	private WorldEditBridge() {
		throw new AssertionError();
	}

	public static WorldEditPlugin getWorldEditPlugin() {
		if (worldEditPlugin == null) {
			try {
				Plugin we = Bukkit.getServer().getPluginManager()
						.getPlugin("WorldEdit");
				if (we != null) {
					if (we instanceof WorldEditPlugin) {
						worldEditPlugin = (WorldEditPlugin) we;
					}
				}
			} catch (Exception ex) {
				plugin.log.info(Level.HIGH.getString + ex);
			}
		}
		return worldEditPlugin;
	}

	public static WorldGuardPlugin getWorldGuard() {
		Plugin plugin = Bukkit.getServer().getPluginManager()
				.getPlugin("WorldGuard");

		// WorldGuard may not be loaded
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null; // Maybe you want throw an exception instead
		}

		return (WorldGuardPlugin) plugin;
	}

	public static BukkitPlayer getBukkitPlayer(Player player) {
		try {
			final WorldEditPlugin wep = getWorldEditPlugin();
			if (wep != null) {
				return wep.wrapPlayer(player);
			}
		} catch (Exception ex) {
			plugin.log.info(Level.HIGH.getString + ex);
		}
		return null;
	}

	public static LocalSession getPlayerSession(Player player) {
		try {
			final WorldEditPlugin wep = getWorldEditPlugin();
			if (wep != null) {
				return wep.getSession(player);
			}
		} catch (Exception ex) {
			plugin.log.info(Level.HIGH.getString + ex);
		}
		return null;
	}

	/**
	 * Undo a players worldedits the given amount.
	 * 
	 * @param player
	 *            - The given player.
	 * @param amount
	 *            - The given amount.
	 * 
	 * @see WorldEditBridge.undo(tpvp, 2);
	 * 
	 * @throws Exception
	 *             if caused
	 */

	public static void undo(Player player, int amount) {
		try {
			LocalSession session = getPlayerSession(player);
			if (session != null) {
				final BukkitPlayer bukkitPlayer = getBukkitPlayer(player);
				if (bukkitPlayer != null) {
					for (int i = 0; i < amount; i++) {
						session.undo(session.getBlockBag(bukkitPlayer),
								bukkitPlayer);
					}
				}
			}
		} catch (Exception ex) {
			plugin.log.info(Level.HIGH.getString + ex);
		}
	}
	
	public static void redo(Player player, int amount) {
		try {
			LocalSession session = getPlayerSession(player);
			if (session != null) {
				final BukkitPlayer bukkitPlayer = getBukkitPlayer(player);
				if (bukkitPlayer != null) {
					for (int i = 0; i < amount; i++) {
						session.redo(session.getBlockBag(bukkitPlayer),
								bukkitPlayer);
					}
				}
			}
		} catch (Exception ex) {
			plugin.log.info(Level.HIGH.getString + ex);
		}
	}

	public static void setLimit(Player player, int limit) {
		try {
			final LocalSession session = getPlayerSession(player);
			if (session != null) {
				session.setBlockChangeLimit(limit);
			}
		} catch (Exception ex) {
			plugin.log.info(Level.HIGH.getString + ex);
		}
	}
	

}