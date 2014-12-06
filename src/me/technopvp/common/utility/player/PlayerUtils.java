package me.technopvp.common.utility.player;

import java.util.List;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.utility.StringUtils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class PlayerUtils {
	static dCommon plugin = dCommon.instance;
	
	FileConfiguration config = plugin.getConfig();
	
    public static void getHead(Player p) {
    	ItemStack skullitem = new ItemStack(Material.SKULL_ITEM);			
    	skullitem.setDurability((short)3);			
    	SkullMeta skullMeta = (SkullMeta)skullitem.getItemMeta();
    	skullMeta.setOwner(p.getName());					
    	skullitem.setItemMeta(skullMeta);
    }
	
	public static boolean spawn(Player p) {
		p.teleport(p.getWorld().getSpawnLocation());
		return true;
	}
	
	public static boolean message(Player player, String s) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
		return true;
	}
	
	public static boolean removeFromCurrentConversation(Player player) {
		Lists.reply.remove(player.getName());
		return true;
	}
	
	/* 20 ticks is one second */
	public static boolean addPotionEffect(Player player, PotionEffectType PotionEffectType, int length, int strength) {
		player.addPotionEffect(new PotionEffect(PotionEffectType, length, strength));
		return true;
	}
	
	public static boolean canWarp(Player player, int range) {
		List<Entity> nearbyEntities = player.getNearbyEntities(range, range, range);

		for (Entity e : nearbyEntities) {
			if ((e instanceof Player)) {
				return true;
		}else {
			return false;
		}
		}
		return false;

	}
	
	public static boolean disableMessages(Player player) {
		Lists.togglepm.add(player.getName());
		return true;
	}
	
	public static boolean enableMessages(Player player) {
		Lists.togglepm.remove(player.getName());
		return true;
	}
	
	public static boolean permission(Player player) {
	    player.sendMessage(StringUtils.translate(plugin.messages.getString("no_permission")));
		return true;
	  }
	
	public static void give(Player player, Material material, int amount) {
		player.getInventory().addItem(new ItemStack(material, amount));
		return;
	}
	
}
