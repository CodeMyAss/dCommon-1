package me.technopvp.common.listeners;

import java.util.Random;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.api.fancymessage.FancyMessage;
import me.technopvp.common.managers.MessageManager;
import me.technopvp.common.utilities.CommonCore;
import me.technopvp.common.utilities.StringUtils;
import me.technopvp.common.utilities.Utils;
import me.technopvp.common.utilities.player.PlayerUtils;
import me.technopvp.common.utilities.player.User;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class PlayerListener extends CommonCore implements Listener {
	dCommon plugin = dCommon.instance;

	FileConfiguration config = plugin.getConfig();

	@EventHandler
	public void onPlayerShotEnderBow(EntityShootBowEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if (Lists.enderbow.contains(player.getName())) {
				player.launchProjectile(EnderPearl.class);
				player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerDamagedWhileInGode(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (User.getUserConfig(player.getName()).getBoolean("GodMode") == true) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void TEST(WeatherChangeEvent event) {
		event.setCancelled(true);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerBrewingDone(BrewEvent event) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.playEffect(event.getBlock().getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
		}
	}

	@EventHandler
	public void onCommandsDisabled(PlayerCommandPreprocessEvent event) {
		if (Lists.disabledcommands == true) {
			if (!event.getPlayer().isOp() || !event.getPlayer().hasPermission("disabledcommands.bypass")) {
				if (event.getMessage().startsWith("/")) {
					event.setCancelled(true);
					if (plugin.messages.contains("commands_disabled_message")) {
						String commands_disabled_message = StringUtils.translate(plugin.messages.getString("commands_disabled_message"));
						commands_disabled_message = commands_disabled_message.replace("%player", event.getPlayer().getName());
						event.getPlayer().sendMessage(commands_disabled_message);
					} else {
						MessageManager.message(event.getPlayer(), ChatColor.RED + "All commands are currently disabled.");
					}
				}
			}
		}
	}

	@EventHandler
	public void onHungerLoss(FoodLevelChangeEvent event) {
		if ((event.getFoodLevel() < ((Player) event.getEntity()).getFoodLevel()) && (new Random().nextInt(100) > 4)) event.setCancelled(true);
	}

	@EventHandler
	public void onItemPickupEvent(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		if (Lists.iname.contains(player.getName())) {
			player.sendMessage(ChatColor.GRAY + "" + event.getItem().getItemStack().getType().toString().toLowerCase() + "(" + ChatColor.RED + event.getItem().getItemStack().getAmount() + ChatColor.GRAY + ")");
		}
	}

	@EventHandler
	public void onBlodEffectForMobs(EntityDamageEvent e) {
		DamageCause damageCause = e.getCause();
		Random random = new Random();
		Entity entity = e.getEntity();
		if (entity instanceof Zombie | entity instanceof Spider | entity instanceof Skeleton) {
			if (damageCause.equals(DamageCause.ENTITY_ATTACK)) {
				// The more the first number is increased the less chance there is.
				if (random.nextInt(4) < 3) {
					e.getEntity().getLocation().getWorld().playEffect(e.getEntity().getLocation().add(0, 1, 0), Effect.STEP_SOUND, 152);
				}
			}
		}
	}

	@EventHandler
	public void onPlayerBreakWhileInSpeed(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if (Lists.speedmode.contains(player.getName())) {
			e.setCancelled(true);
			player.sendMessage(ChatColor.GRAY + "You can't break blocks while in speed mode.");
		}
	}

	@EventHandler
	public void onPlayerSpongeJumpEvent(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		if (event.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SPONGE) {
			Vector vector = player.getLocation().getDirection();
			vector.multiply(2);
			vector.setY(1.5);

			player.setVelocity(vector);
			player.playSound(player.getLocation(), Sound.SHOOT_ARROW, 20, 60);
			Lists.spongeJumping.add(player.getName());
		}
	}

	@EventHandler
	public void onPlayerCreativeDroppingEvent(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if (Lists.cdrop.contains(player.getName())) {
			if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
				e.getBlock().breakNaturally();
			}
		}
	}

	@EventHandler
	public void onPlayerDeathCordsEvent(PlayerDeathEvent e) {
		Player player = e.getEntity();
		Location loc = player.getLocation();
		if (player instanceof Player) {
			if (player.hasPermission("deathcords.yes")) {
				new FancyMessage(ChatColor.GOLD + "You have just died!").tooltip(ChatColor.GRAY + "" + ChatColor.RED + ChatColor.BOLD + "DeathCords: " + ChatColor.GRAY + "X: " + ChatColor.RED + loc.getBlockX() + ChatColor.GRAY + " Y: " + ChatColor.RED + loc.getBlockY() + ChatColor.GRAY + " Z: " + ChatColor.RED + loc.getBlockZ()).send(player);
				e.setDeathMessage(null);

			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerSpeedEvent(EntityDamageEvent event) {
		if ((event.getEntity() instanceof Player)) {
			Player player = (Player) event.getEntity();
			if (Lists.speedmode.contains(player.getDisplayName())) event.setCancelled(true);
			player.setFireTicks(0);
			player.setFallDistance(0);
		}
	}

	@EventHandler
	public void onPlayerClickWhileInSpeedMode(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (Lists.speedmode.contains(player.getName())) {
			if (e.getAction() == Action.LEFT_CLICK_AIR) {
				e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(config.getInt("speedmode-speed")));
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteractWithTnt(PlayerInteractEvent event) {
		if (Lists.instatnt.contains(event.getPlayer().getName())) {
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (event.getClickedBlock().getType().equals(Material.TNT)) {
					event.getClickedBlock().setType(Material.AIR);
					event.getPlayer().getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0, 1, 0), EntityType.PRIMED_TNT);
				}
			}
		}
	}

	@EventHandler
	public void onPlayerClickSign(PlayerInteractEvent e) {
		try {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Sign s = (Sign) e.getClickedBlock().getState();
				if (s.getLine(0).equalsIgnoreCase("Welcome to")) {
					e.getPlayer().sendMessage("§bWelcome " + ChatColor.RESET + e.getPlayer().getDisplayName() + "§b to " + ChatColor.DARK_PURPLE + ChatColor.BOLD + StringUtils.getServerName());
					e.getPlayer().sendMessage("§bDonate at " + ChatColor.RED + StringUtils.getDonationPage());
					e.getPlayer().sendMessage("§7Coder: §9TechnoPvP");
				}
			}
		} catch (Exception ex) {
		}
	}

	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		if (event.getLine(0).equalsIgnoreCase("[Welcome]")) {
			event.setLine(0, "Welcome to");
			event.setLine(1, ChatColor.BLUE + StringUtils.getServerName());
			event.setLine(2, "§cClick Here");

		}
	}

	@SuppressWarnings("unused")
	@EventHandler(priority = EventPriority.MONITOR)
	public void onSignColor(SignChangeEvent event) {
		if (!event.getPlayer().hasPermission("signcolor.yes")) return;

		Block block = event.getBlock();
		Sign sign = null;
		Material type = block.getType();
		if ((type.equals(Material.SIGN)) || (type.equals(Material.SIGN_POST)) || (type.equals(Material.WALL_SIGN))) sign = (Sign) block.getState();
		else {
			return;
		}
		String[] text = event.getLines();
		int count = 0;
		for (String line : text) {
			line = line.replaceAll("&", "§");
			line = line.replaceAll("§§", "&");
			event.setLine(count, line);
			count++;
		}
	}

	@EventHandler
	public void onRandomMineChance(BlockBreakEvent event) {
		if (Utils.getChance(18)) {
			if (event.getBlock().getType().equals(Material.IRON_ORE)) {
				event.getBlock().setType(Material.AIR);
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT, 1));
			}
			if (event.getBlock().getType().equals(Material.GOLD_ORE)) {
				event.getBlock().setType(Material.AIR);
				event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, 1));
			}
		}
	}

	@EventHandler
	public void onPlayerAboveNether(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (plugin.getConfig().getBoolean("allow-above-nether") != true) {
			if (player.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
				if (!player.hasPermission("abovenether.bypass")) {
					int yy = player.getLocation().getBlockY();
					if (yy >= 128.0D) {
						PlayerUtils.spawn(player);
						String disallow_above_nether = StringUtils.translate(plugin.messages.getString("disallow_above_nether"));
						disallow_above_nether = disallow_above_nether.replaceAll("%player", event.getPlayer().getName());
						player.sendMessage(disallow_above_nether);
						player.setFallDistance(0.0F);
					}
				}
			}
		}
	}

	@EventHandler
	public void onAboveNetherInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (plugin.getConfig().getBoolean("allow-above-nether") != true) {
			if (player.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
				if (!player.hasPermission("abovenether.bypass")) {
					int yy = player.getLocation().getBlockY();
					if (yy >= 128.0D) {
						PlayerUtils.spawn(player);
						String disallow_above_nether = StringUtils.translate(plugin.messages.getString("disallow_above_nether"));
						disallow_above_nether = disallow_above_nether.replaceAll("%player", event.getPlayer().getName());
						player.sendMessage(disallow_above_nether);
						player.setFallDistance(0.0F);
					}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerFallVoid(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (config.getBoolean("allow-void-falling") == false) if (player.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
			int yy = player.getLocation().getBlockY();
			if (yy <= -40) {
				PlayerUtils.spawn(player);
				String void_error_message = StringUtils.translate(plugin.messages.getString("void_error_message"));
				void_error_message = void_error_message.replaceAll("%player", event.getPlayer().getName());
				player.sendMessage(void_error_message);
				player.setFallDistance(0);
			}
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (Lists.frozen.contains(player.getName())) {
			if (event.getTo().getX() == event.getFrom().getX() && event.getTo().getZ() == event.getFrom().getZ()) return;
			event.setTo(event.getFrom());
		}
	}

	@EventHandler
	public void onPlayerPickUpItem(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		if (Lists.nopick.contains(player.getName())) {
			event.setCancelled(true);
		}
	}

}
