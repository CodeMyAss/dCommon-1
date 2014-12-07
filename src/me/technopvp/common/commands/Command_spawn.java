package me.technopvp.common.commands;

import java.util.ArrayList;

import me.technopvp.common.Lists;
import me.technopvp.common.managers.MessageManager;
import me.technopvp.common.managers.SpawnManager;
import me.technopvp.common.utilities.config.SpawnConfig;
import me.technopvp.common.utilities.enums.Level;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Permissions.Permission;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_spawn extends CommonCommand {

	ArrayList<String> spawns = new ArrayList<String>();
	String teleporting = Gray + "Teleporting....";

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		final Player player = (Player)sender;
		for (String configs : SpawnConfig.getInstance().getSpawnConfig().getConfigurationSection("spawns").getKeys(false)) {
			spawns.add(configs);
		}

		if (args.length == 0) {
			if (!spawns.contains("default")) {
				player.sendMessage(DarkRed + "Error: " + White + " the spawn location is not set.");
				MessageManager.log(Level.FATAL, "Spawn location is not set. Perfom /setspawn");
				return true;
			}
				if (!spawns.contains(plugin.permission.getPrimaryGroup(player))) {
				if (!player.isOp()) {
					player.sendMessage(teleporting);
					player.sendMessage(Gray + "Teleportation will commence in " + Red + "5 seconds" + Gray + ". Don't move.");
					Lists.teleportingSpawn.add(player.getName());
					new SpawnManager(Bukkit.getScheduler(), plugin, 100, 0, 1) {

						@Override
						public void onRun() {
							if (Lists.teleportingSpawn.contains(player.getName())) {
							player.teleport(SpawnConfig.getInstance().getDefaultSpawnLocation());
							}
						}
					};
				}else {
					/* If the player has instant teleport */
					player.sendMessage(teleporting);
					player.teleport(SpawnConfig.getInstance().getDefaultSpawnLocation());
					return true;
				}
				} else {
					if (!player.isOp()) {
						player.sendMessage(teleporting);
						player.sendMessage(Gray + "Teleportation will commence in " + Red + "5 seconds" + Gray + ". Don't move.");
						Lists.teleportingSpawn.add(player.getName());
						new SpawnManager(Bukkit.getScheduler(), plugin, 100, 0, 1) {

							@Override
							public void onRun() {
								if (Lists.teleportingSpawn.contains(player.getName())) {
									player.teleport(SpawnConfig.getInstance().getGroupSpawn(player));
								}
							}
						};
					}else {
						player.sendMessage(teleporting);
						player.teleport(SpawnConfig.getInstance().getGroupSpawn(player));
						return true;
					}
			}
			return true;
		}
		if (args.length >= 1) {
		@SuppressWarnings("deprecation")
			Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			targetOffline(args[0]);
			return true;
		}
				if (!spawns.contains(plugin.permission.getPrimaryGroup(player))) {
							target.teleport(SpawnConfig.getInstance().getDefaultSpawnLocation());
				} else {
					target.teleport(SpawnConfig.getInstance().getGroupSpawn(target));
			}
			player.sendMessage(Gray + "Teleporting " + Red + target.getName() + Gray + " to spawn.");
			target.sendMessage(Gray + "Teleporting to spawn.");
			return true;
		}
		if (args.length == 3) {
			return true;
		}
		return true;
	}

}
