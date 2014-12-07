package me.technopvp.common.utilities.enums;

import org.bukkit.ChatColor;

public enum Level {
	
	LOW(ChatColor.GRAY + "WARNING" + ChatColor.RESET), 
	MEDIUM(ChatColor.RED + "WARNING" + ChatColor.RESET),
	HIGH(ChatColor.DARK_RED + "WARNINING" + ChatColor.RESET),
	FATAL("" + ChatColor.DARK_RED + ChatColor.BOLD + "WARNING");
			
	public String getString;
	
	private Level(String string) {
		getString = string;
	}

	public String toString() {
		return getString;
	}
	
}
