package me.technopvp.common.commands;

import java.util.ArrayList;
import java.util.List;

import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.ADMIN)
public class Command_enchant extends CommonCommand {
	//TODO Finish Enchanting Command
	public boolean run(CommandSender sender, Command cmd, String[] args) {
			if (args.length == 0 || args.length > 2) {
				showUsage(cmd);
				return true;
			}
			Player player = (Player) sender;
			if(player.getInventory().getItemInHand() == null || player.getInventory().getItemInHand().getType().equals(Material.AIR)) {
				invalidUsage();
				msg("You must be holding an item.");
				return true;
			}
			if (args.length == 1) {
				Enchantment ench = Enchantment.getByName(enchantFinder(args[0]));
				if(ench == null) {
					invalidImput();
					msg("Enchantment not found.");
					return true;
				}
				int level = ench.getStartLevel();
				if(ench.canEnchantItem(player.getInventory().getItemInHand())) {
					player.getInventory().getItemInHand().addEnchantment(ench, level);
					player.sendMessage(Gray + "Added the enchantment " + Red + trueName(ench.getName()) + Gray + " at level " + Red +Integer.toString(level) + Gray + ".");
					return true;
				}
				player.sendMessage("This item can not support given enchantment.");
				return true;
			}
			
			if (args.length == 2) {
					Enchantment ench = Enchantment.getByName(enchantFinder(args[0]));
					if(ench == null) {
						player.sendMessage( "Error: " + "Enchantment does not exist.");
						return true;
					}
				int level = -1;
				if(!args[1].equalsIgnoreCase("max")) {
					try {
						Integer.parseInt(args[1]);
					} catch(Exception e) {
						invalidImput();
						msg("You must enter a valid enchantment level.");
						return true;
					}
					level = Integer.parseInt(args[1]);
				}
				
				if(player.hasPermission("Necessities.unsafeEnchant") && level > 127) {
					level = 127;
				}
				
				if(!player.hasPermission("Necessities.unsafeEnchant")) {
					if (level > ench.getMaxLevel()) {
						invalidImput();
						msg("That level is to large for that enchantment.");
						return true;
					}
					
					level = ench.getMaxLevel();
				if(ench.canEnchantItem(player.getInventory().getItemInHand()) || player.hasPermission("enchant.bypass")) {
					if(level == -1) {
						player.getInventory().getItemInHand().addUnsafeEnchantment(ench, ench.getMaxLevel());
						player.sendMessage(Gray + "Added the enchantment " + Red +  trueName(ench.getName()) + Gray + " at max level.");
						return true;
					}
						if(level == 0) {
							player.getInventory().getItemInHand().removeEnchantment(ench);
							player.sendMessage(Gray + "Removed enchantment " + Red + trueName(ench.getName()) + Gray +  ".");
							return true;
						} else {
							player.getInventory().getItemInHand().addUnsafeEnchantment(ench, level);
							player.sendMessage(Gray + "Added the enchantment " + Red + trueName(ench.getName()) + Gray + " at level " + Red + Integer.toString(level) + Gray + ".");
						}
					}else {
						invalidUsage();
						msg("You can't use that enchantent on that item.");
						return true;
					}
					return true;
				}
			}
			return true;
	}
	
	public List<String> tabComplete(CommandSender sender, String[] args) {
    	List<String> complete = new ArrayList<String>();
    	String search = "";
    	if(args.length > 0)
    		search = args[args.length - 1];
    	search = search.toUpperCase();
    	if(sender instanceof Player) {
    		Player p = (Player) sender;
	    	if(args.length == 1) {
	    		if("ALL".startsWith(search))
					complete.add("ALL");
				for(Enchantment e : Enchantment.values())
					if(e.canEnchantItem(p.getInventory().getItemInHand()) && e.getName().startsWith(search))
						complete.add(e.getName());
	    	} else {
	    		if("MAX".startsWith(search))
					complete.add("MAX");
	    		for(Enchantment e : Enchantment.values())
					if(e.canEnchantItem(p.getInventory().getItemInHand()) && e.equals(Enchantment.getByName(enchantFinder(args[0]))) &&
							Integer.toString(e.getMaxLevel()).startsWith(search))
						for(int i = 0; i < e.getMaxLevel(); i++)
							complete.add(Integer.toString(i + 1));
	    	}
		}
		return complete;
    }
	
	private String enchantFinder(String enchant) {
		enchant = enchant.toUpperCase();
		if(enchant.equals("POWER"))
			enchant = "ARROW_DAMAGE";
		else if(enchant.equals("FLAME"))
			enchant = "ARROW_FIRE";
		else if(enchant.equals("INFINITY"))
			enchant = "ARROW_INFINITY";
		else if(enchant.equals("PUNCH"))
			enchant = "ARROW_KNOCKBACK";
		else if(enchant.equals("SHARPNESS"))
			enchant = "DAMAGE_ALL";
		else if(enchant.equals("BANEOFARTHROPODS"))
			enchant = "DAMAGE_ARTHROPODS";
		else if(enchant.equals("BANE"))
			enchant = "DAMAGE_ARTHROPODS";
		else if(enchant.equals("SMITE"))
			enchant = "DAMAGE_UNDEAD";
		else if(enchant.equals("EFFICIENCY"))
			enchant = "DIG_SPEED";
		else if(enchant.equals("UNBREAKING"))
			enchant = "DURABILITY";
		else if(enchant.equals("FIREASPECT"))
			enchant = "FIRE_ASPECT";
		else if(enchant.equals("FORTUNE"))
			enchant = "LOOT_BONUS_BLOCKS";
		else if(enchant.equals("LOOTING"))
			enchant = "LOOT_BONUS_MOBS";
		else if(enchant.equals("RESPIRATION"))
			enchant = "OXYGEN";
		else if(enchant.equals("PROTECTION"))
			enchant = "PROTECTION_ENVIRONMENTAL";
		else if(enchant.equals("BLASTPROTECTION"))
			enchant = "PROTECTION_EXPLOSIONS";
		else if(enchant.equals("FEATHERFALLING"))
			enchant = "PROTECTION_FALL";
		else if(enchant.equals("FIREPROTECTION"))
			enchant = "PROTECTION_FIRE";
		else if(enchant.equals("PROJECTILEPROTECTION"))
			enchant = "PROTECTION_PROJECTILE";
		else if(enchant.equals("SILKTOUCH"))
			enchant = "SILK_TOUCH";
		else if(enchant.equals("AQUAINFINITY"))
			enchant = "WATER_WORKER";
		return enchant;
	}
	
	private String trueName(String enchant) {
		if(enchant.equals("ARROW_DAMAGE"))
			enchant = "power";
		else if(enchant.equals("ARROW_FIRE"))
			enchant = "flame";
		else if(enchant.equals("ARROW_INFINITY"))
			enchant = "infinity";
		else if(enchant.equals("ARROW_KNOCKBACK"))
			enchant = "punch";
		else if(enchant.equals("DAMAGE_ALL"))
			enchant = "sharpness";
		else if(enchant.equals("DAMAGE_ARTHROPODS"))
			enchant = "bane of arthropods";
		else if(enchant.equals("DAMAGE_UNDEAD"))
			enchant = "smite";
		else if(enchant.equals("DIG_SPEED"))
			enchant = "efficiency";
		else if(enchant.equals("DURABILITY"))
			enchant = "unbreaking";
		else if(enchant.equals("FIRE_ASPECT"))
			enchant = "fire aspect";
		else if(enchant.equals("LOOT_BONUS_BLOCKS"))
			enchant = "fortune";
		else if(enchant.equals("LOOT_BONUS_MOBS"))
			enchant = "looting";
		else if(enchant.equals("OXYGEN"))
			enchant = "respiration";
		else if(enchant.equals("PROTECTION_ENVIRONMENTAL"))
			enchant = "protection";
		else if(enchant.equals("PROTECTION_EXPLOSIONS"))
			enchant = "blast protection";
		else if(enchant.equals("PROTECTION_FALL"))
			enchant = "feather falling";
		else if(enchant.equals("PROTECTION_FIRE"))
			enchant = "fire protection";
		else if(enchant.equals("PROTECTION_PROJECTILE"))
			enchant = "projectile protection";
		else if(enchant.equals("SILK_TOUCH"))
			enchant = "silk touch";
		else if(enchant.equals("WATER_WORKER"))
			enchant = "aqua infinity";
		return enchant.toLowerCase();
	}
}