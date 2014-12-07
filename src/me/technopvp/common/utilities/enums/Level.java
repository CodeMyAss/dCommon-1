package me.technopvp.common.utilities.enums;

import org.bukkit.ChatColor;

public enum Level {

	/**
	 * The lowest level that can be logged, actions won't be taken.
	 */
	LOW(ChatColor.GRAY + "WARNING" + ChatColor.RESET),
	/**
	 * A medium level to be logged at, user will be informed.
	 */
	MEDIUM(ChatColor.RED + "WARNING" + ChatColor.RESET),
	/**
	 * High level logging. Actions will be taken to the consol, and inform the consol.
	 */
	HIGH(ChatColor.DARK_RED + "WARNINING" + ChatColor.RESET),
	/**
	 * FATAL action has occoured, server takes maxium action.
	 */
	FATAL("" + ChatColor.DARK_RED + ChatColor.BOLD + "WARNING");

	private String prefix;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	private Level(String prefix) {
		this.prefix = prefix;
	}
}
