package me.technopvp.common.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.utility.CommonCore;
import me.technopvp.common.utility.StringUtils;
import me.technopvp.common.utility.Utils;
import me.technopvp.common.utility.player.User;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class JoinListener extends CommonCore implements Listener {
	dCommon plugin = dCommon.instance;

	@EventHandler(priority=EventPriority.HIGH)
	  public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		/* Save the user data information. */
        User user = plugin.user.getUser(player.getName());
        user.getPlayerConfig().getConfig().options().copyDefaults(true);
        user.getPlayerConfig().reloadPlayerConfig();
        user.getPlayerConfig().savePlayerConfig();

        /*Load the user data information */
        User.loadUserData(player);

		/* Get the 'join messages' in the messages.yml, and replace all the strings */
		  String JoinMessage = StringUtils.translate(plugin.messages.getString("join_message"));
		  JoinMessage = JoinMessage.replaceAll("%player", event.getPlayer().getName());

		  /* If the player is not on the VIP list set there join message to the message.yml join_message */
		  if (!Utils.isVip(player) == true) {
			  event.getJoinMessage();
			  event.setJoinMessage(JoinMessage);
		  }else {
			  /* If the player is on the VIP list set the join message to be GOLD. */
			  event.setJoinMessage(ChatColor.GRAY + "" + ChatColor.GOLD + ChatColor.BOLD + event.getPlayer().getName() + " has joined the game");
		  }
	  }

	@EventHandler
	public void onPlayerJoinWhenLocked(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (Lists.locked == true) {
			if (!player.hasPermission("locked.join")) {
			}
		}
	}

    @EventHandler
    public void PlayerMessageMOTD(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("motd.yes")) {
            try {
                File file = new File(plugin.getDataFolder(), "motd.txt");
                Scanner s = new Scanner(file);
                while (s.hasNextLine()) {
                	String string = s.nextLine();
                	string = string.replace("<online>", "" + Bukkit.getOnlinePlayers().length);
                	string = string.replace("<server>", "" + Utils.serverName());
                	string = string.replace("<donationpage>", "" + Utils.dPage());
                	string = string.replace("<world>", "" + player.getWorld());
                	string = string.replace("<name>", "" + player.getName());
                	string = string.replace("<website>", "" + Utils.website());
                	string = string.replace("<displayname>", "" + player.getDisplayName());
                	string = string.replace("\n", "\n");

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
                }
                s.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

	@EventHandler
	  public void onPlayerFirstJoin(PlayerJoinEvent e) {
	    Player player = e.getPlayer();
	    /* If It's allowed in the configeration file */
	    if (plugin.getConfig().getBoolean("join-fireworks") == true) {
	    	/* If they havent played before */
	    if (!player.hasPlayedBefore()) {
	      Firework fw;
	      for (int i = 0; i < 4; i++) {
	        fw = (Firework)e.getPlayer().getLocation().getBlock().getLocation().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.FIREWORK);
	        FireworkMeta fm = fw.getFireworkMeta();
	        fm.addEffect(FireworkEffect.builder()
	          .flicker(true)
	          .trail(true)
	          .with(FireworkEffect.Type.STAR)
	          .withColor(Color.PURPLE)
	          .withFade(Color.AQUA)
	          .build());
	        fm.setPower(1);
	        fw.setFireworkMeta(fm);
	      }
	      player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
	    }
	    }
	  }

	@EventHandler
	  public void joinGhostCheck(PlayerJoinEvent event) {
		  Player player = event.getPlayer();
			  plugin.ghostManager.setGhost(player, false);
	  }

	@EventHandler
	  public void joinAsVanished(PlayerJoinEvent e) {
	    Player pl = e.getPlayer();
	    for (Player pls : Bukkit.getOnlinePlayers())
	      if (Lists.vanish.contains(pls.getName()))
	        pls.hidePlayer(pl);
	  }

}
