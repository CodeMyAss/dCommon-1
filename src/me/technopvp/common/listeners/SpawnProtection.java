package me.technopvp.common.listeners;

import me.technopvp.common.Lists;
import me.technopvp.common.utility.player.LocationUtils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class SpawnProtection implements Listener {

	  @EventHandler
	  public void AttemptToDamageProtected(EntityDamageByEntityEvent event) {
		  Entity entity = event.getEntity();
		  if (entity instanceof Player) {
			  Player damagedPlayer = (Player) event.getEntity();
			  Player damager = (Player) event.getDamager();

			  if (Lists.spawnProtected.contains(damagedPlayer.getName())) {
				  damager.sendMessage(ChatColor.RED + "This player has spawn protection.");
				  event.setCancelled(true);
			  }
		  }
	  }

	  @EventHandler
	  public void RespawnPlayer(PlayerRespawnEvent event) {
		  Player player = event.getPlayer();

		  player.sendMessage(ChatColor.GRAY + "You have regained spawn protection.");
		  Lists.spawnProtected.add(event.getPlayer().getName());
	  }


	  @EventHandler
	  public void RemoveOnTeleport(PlayerTeleportEvent event) {
		  Player player = event.getPlayer();

		  /* Player is teleporting out of spawn, and is spawn protected */
		  if (!LocationUtils.insideLocation(event.getTo(), 35) || Lists.spawnProtected.contains(player.getName())) {
			  player.sendMessage(ChatColor.GRAY + "You no longer have spawn protection.");
			  Lists.spawnProtected.remove(player.getName());
		  }
		  }

	  @EventHandler
	  public void CheckIsProtected(PlayerMoveEvent event) {
		  Player player = event.getPlayer();

		  if (!LocationUtils.insideLocation(player.getLocation(), 35)) {
			  if (Lists.spawnProtected.contains(player.getName())) {
				  Lists.spawnProtected.remove(player.getName());
			  player.sendMessage(ChatColor.GRAY + "You no longer have spawn protection.");
		  }
		  }
	  }

}
