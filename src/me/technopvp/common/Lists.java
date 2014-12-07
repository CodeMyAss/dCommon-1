package me.technopvp.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public class Lists  {

   	/*
   	 * ArrayList's
   	 */
   	public static ArrayList<String> nopick = new ArrayList<String>();
   	public static ArrayList<String> speedmode = new ArrayList<String>();
   	public static ArrayList<String> nokick = new ArrayList<String>();
   	public static ArrayList<String> instatnt = new ArrayList<String>();
   	public static ArrayList<String> cdrop = new ArrayList<String>();
   	public static ArrayList<String> iname = new ArrayList<String>();
   	public static ArrayList<String> enderbow = new ArrayList<String>();
   	public static ArrayList<String> namesound = new ArrayList<String>();
   	public static ArrayList<String> dcommand = new ArrayList<String>();
   	public static ArrayList<String> frozen = new ArrayList<String>();
   	public static ArrayList<String> staff = new ArrayList<String>();
   	public static ArrayList<String> staffchat = new ArrayList<String>();
   	public static ArrayList<String> vanish = new ArrayList<String>();
   	public static ArrayList<String> forcedgrammar = new ArrayList<String>();
   	public static ArrayList<String> togglepm = new ArrayList<String>();
   	public static ArrayList<String> togglechat = new ArrayList<String>();
   	public static ArrayList<String> spongeJumping = new ArrayList<String>();
	public static ArrayList<String> spawnProtected = new ArrayList<String>();
	public static ArrayList<String> teleportingSpawn = new ArrayList<String>();


	/*
	 * HashMap's
	 */
	public static Map<String, Long> slowtime = new HashMap<String, Long>();
	public static Map<String, ChatColor> chatcolor = new HashMap<String, ChatColor>();
	public static HashMap<String, String> reply = new HashMap<String, String>();
	public static HashMap<String, ItemStack[]> inventory = new HashMap<String, ItemStack[]>();
	public static HashMap<String, ItemStack[]> armor = new HashMap<String, ItemStack[]>();

	/*
	 *
	 * Boolean's
	 */
   	public static boolean chatMuted = false;
   	public static boolean locked = false;
   	public static boolean slowchat = false;
   	public static boolean slowstop = false;
   	public static boolean disabledcommands = false;

}