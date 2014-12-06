package me.technopvp.common.enums;

public enum BroadcastType {

	/**
	 *  Warning broadcast, broadcasted when something of attention has occoured
	 */
	WARNING(Level.HIGH, "&4WARNING "),
	/**
	 *  Information broadcast. broadcaste information to the server.
	 */
	INFO(Level.MEDIUM, "&7[&cINFO&7] "),
	/**
	 *  Trigger when there is a server message. [Server]
	 */
	SERVER(Level.HIGH, "&d[Server] "),
	/**
	 *  Broadcast when a tip is needed. I.E. 'TIP Make sure to check out our website!'
	 */
	TIP(Level.LOW, "&bTIP "),
	/**
	 *  A normal, default broadcast.
	 */
	NORMAL(Level.LOW, "&7[&cbroadcast&7] ");

	private Level level;
	private String prefix;

	private BroadcastType(Level level, String prefix) {
		this.level = level;
		this.prefix = prefix;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
