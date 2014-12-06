package me.technopvp.common.utility.player;

import me.technopvp.common.Lists;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

/** 
 * @author tpvp
 **/

public class InventoryUtils {
	
	public void storePlayerInventory(Player player){
		PlayerInventory pinv = player.getInventory();
		Lists.inventory.put(player.getName(), pinv.getContents());
		pinv.clear();
	}

	public void storePlayerArmor(Player player){
		PlayerInventory pinv = player.getInventory();
		Lists.armor.put(player.getName(), pinv.getArmorContents());
		pinv.setArmorContents(null);
	}

	public void restorePlayerInventory(Player player){
		player.getInventory().setContents(Lists.inventory.get(player.getName()));
		Lists.inventory.remove(player.getName());
	}

	public void restorePlayerArmor(Player player){
		player.getInventory().setArmorContents(Lists.armor.get(player.getName()));
		Lists.armor.remove(player.getName());
	}
	
	public void restorePlayerLoadout(Player player){
		player.getInventory().setArmorContents(Lists.armor.get(player.getName()));
		player.getInventory().setContents(Lists.inventory.get(player.getName()));
		Lists.armor.remove(player.getName());
		Lists.inventory.remove(player.getName());
	}
	
	public static void clearInventory(Player player) {
		player.getInventory().clear();
	}
	
	public static void clearArmor(Player player) {
		player.getInventory().clear();
		player.getInventory().setBoots(null);
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
	}
	
	public static void clearLoadout(Player player) {
		player.getInventory().clear();
		InventoryUtils.clearArmor(player);
	}
}