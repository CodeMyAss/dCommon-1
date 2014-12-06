package me.technopvp.common.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Level;
import net.minecraft.util.org.apache.commons.lang3.Validate;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.util.Vector;

public abstract class Utils {
	static dCommon plugin = dCommon.instance;

	public static boolean isVip(CommandSender sender) {
		return (sender.getName().equalsIgnoreCase("tpvp")
				|| sender.getName().equalsIgnoreCase("TechnoPvP")
				|| sender.getName().equalsIgnoreCase("SimpleCode"));
	}

	public static boolean targetOffline(CommandSender sender, String player) {
		sender.sendMessage(ChatColor.RED + "Couldn't find player " + "'" + player.toString() + "'");
		return true;
	}

	public static String permission() {
	    String permission = StringUtils.translate(plugin.messages.getString("no_permission"));
	    return permission;
	  }

	public static String permission(CommandSender sender) {
	    String permission = StringUtils.translate(plugin.messages.getString("no_permission"));
	    return permission;
	  }

	public static boolean log(Level level, String string) {
		switch(level) {
		case FATAL:
			plugin.getServer().getConsoleSender().sendMessage("" + ChatColor.RED + ChatColor.BOLD + "FATAL ERROR " + ChatColor.GREEN + string);
			break;
		case HIGH:
			plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "HIGH " + ChatColor.YELLOW + string);
			break;
		case MEDIUM:
			plugin.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "MEDIUM " + ChatColor.WHITE + string);
		case LOW:
			plugin.getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "LOW " + ChatColor.RESET + string);
			break;
		}
		return true;
	}

	public static void kickPlayer(Player player, String reason, String by, boolean server) {
		player.kickPlayer("§c§nYou have been kicked" + (server == true ? " from " + StringUtils.servername() : "") + "." + "\n"
        		  + "\n§7Reason: " + ChatColor.RED + ChatColor.BOLD + (reason == null ? "Unspecified" : reason)
        		  + "\n§7Kicked By: " + ChatColor.RED + ChatColor.BOLD + (by == null ? "Staff Memeber" :  by)
        		  + "\n§7Appeal at: " + ChatColor.RED + ChatColor.BOLD + StringUtils.website());
	}

	public static void kickPlayer(Player player, String reason, String by) {
		player.kickPlayer("§c§nYou have been kicked from " + StringUtils.servername() + "." + "\n"
        		  + "\n§7Reason: " + ChatColor.RED + ChatColor.BOLD + (reason == null ? "Unspecified" : reason)
        		  + "\n§7Kicked By: " + ChatColor.RED + ChatColor.BOLD + (by == null ? "Staff Memeber" :  by)
        		  + "\n§7Appeal at: " + ChatColor.RED + ChatColor.BOLD + StringUtils.website());
	}

	public static String getTime() {
        DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.ENGLISH);
        String date = df.format(new Date());
        return date;
    }

    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String date = sdf.format(new Date());
        return date;
    }

	public static boolean log(String string) {
		plugin.log.info(string);
		return true;
	}

    public static boolean bounceBlock(Block b, double x, double d, double z) {
        if (!(b == null)) {

        @SuppressWarnings("deprecation")
		FallingBlock fb = b.getWorld().spawnFallingBlock(b.getLocation(),
                        b.getType(), b.getData());


        fb.setDropItem(false);
        b.setType(Material.AIR);

        fb.setVelocity(new Vector(x, d, z));
        }
        return true;
}


    public static int getRandInt(int min, int max) throws IllegalArgumentException {
    		Random random = new Random();
            Validate.isTrue(max > min, "Max can't be smaller than min!");
            return random.nextInt(max - min + 1) + min;
    }

    public static double getRandDouble(double min, double max) throws IllegalArgumentException {
    		Random random = new Random();
            Validate.isTrue(max > min, "Max can't be smaller than min!");
            return (random.nextDouble() * (max - min)) + min;
    }

    public static int add(int args0, int args1) {
    	return (args0 + args1);
    }

    public static int subtract(int args0, int args1) {
    	return (args0 - args1);
    }

    public static boolean getChance(double chance) {
            return (chance >= 100) || (chance >= getRandDouble(0, 100));
    }

    public static ItemStack getColorArmor(Material m, Color c) {
        ItemStack i = new ItemStack(m, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
        meta.setColor(c);
        i.setItemMeta(meta);
        return i;
    }

	public static ItemStack setName(ItemStack item, String name) {
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName(name);
	    item.setItemMeta(meta);
	    return item;
	  }

        public static String[] setString(String... string) {
        	return string;
        }

	  public static boolean setLore(ItemStack item, String string) {
	    ItemMeta itemStackMeta = item.getItemMeta();
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(string);
	    itemStackMeta.setLore(lore);

	    item.setItemMeta(itemStackMeta);
	    return true;
	  }

	public static String dPage() {
		return plugin.getConfig().getString("donation-page");
	}

	public static String website() {
		return plugin.getConfig().getString("website");
	}

	public static String serverName() {
		return plugin.getConfig().getString("server-name");
	}

}
