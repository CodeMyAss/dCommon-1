package me.technopvp.common.listeners;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.CommonCore;
import me.technopvp.common.utilities.StringUtils;
import me.technopvp.common.utilities.Utils;
import me.technopvp.common.utilities.player.User;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener extends CommonCore implements Listener {
	dCommon plugin = dCommon.instance;

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		/* Set there user data when the player leaves */
		User.loadUserData(player);
		
		/* Get the 'leave message' from the message.yml */
		String LeaveMessage = StringUtils.translate(plugin.messages.getString("leave_message"));
		LeaveMessage = LeaveMessage.replaceAll("%player", event.getPlayer().getName());

		/* If tht player is not on the VIP list set default message */
		if (!Utils.isVip(player)) {
			Lists.slowtime.remove(player);
			event.getQuitMessage();
			event.setQuitMessage(LeaveMessage);
		} else {
			/* If the user is on the VIP list */
			event.setQuitMessage(ChatColor.GRAY + "" + ChatColor.GOLD + ChatColor.BOLD + event.getPlayer().getName() + " has left the game");
		}
	}

}
