package me.technopvp.common.integration;

import me.technopvp.Teams.main.Teams;
import me.technopvp.common.dCommon;
import me.technopvp.common.managers.MessageManager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class TeamsBridge {
	static dCommon plugin = dCommon.instance;

	private static Teams teamsPlugin = null;

	public static Teams getTeamsPlugin() {
		if (teamsPlugin == null) {
			try {
				Plugin we = Bukkit.getServer().getPluginManager().getPlugin("Teams");
				if (we != null) {
					if (we instanceof Teams) {
						teamsPlugin = (Teams) we;
					}
				}
			} catch (Exception ex) {
				MessageManager.log(ex.toString());
			}
		}
		return teamsPlugin;
	}

	public static boolean pluginEnabled() {
		return (getTeamsPlugin().isEnabled());
	}

}
