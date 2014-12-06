package me.technopvp.common.listeners;

import me.technopvp.common.Lists;
import me.technopvp.common.dCommon;
import me.technopvp.common.managers.SpamManager;
import me.technopvp.common.utility.StringUtils;
import me.technopvp.common.utility.CommonCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatListener extends CommonCore implements Listener {
	dCommon plugin = dCommon.instance;
	
//	@EventHandler
//	public void FormatChat(AsyncPlayerChatEvent event) {
//		Player player = event.getPlayer();
//		
//		String chatFormat = plugin.getConfig().getString("config.chat-format");
//		chatFormat = chatFormat.replaceAll("<group>",(plugin.permission.getPlayerGroups(player) != null ? plugin.permission.getPrimaryGroup(player) : "none"));
//		chatFormat = chatFormat.replaceAll("<name>", (player.getName() != null ? player.getName() : "none"));
//		chatFormat = chatFormat.replaceAll("<player>", (player.getName() != null ? player.getName() : "none"));
//		chatFormat = chatFormat.replaceAll("<displayname>", (player.getDisplayName() != null ? player.getDisplayName() : "none"));
//		chatFormat = chatFormat.replaceAll("<world>", (player.getWorld() != null ? player.getWorld().getName(): "none"));
//		chatFormat = chatFormat.replaceAll("<team>", (TeamManager.getInstance().getTeam(player) != null ? TeamManager.getInstance().getTeam(player).getName() : "none"));
//		chatFormat = chatFormat.replaceAll("<message>", event.getMessage());
//		chatFormat = chatFormat.replaceAll("&", "§");
//		
//		event.setFormat(chatFormat);
//	}
	
	@EventHandler
	  public void SpeakWhileMuted(AsyncPlayerChatEvent event) {
	    Player chatter = event.getPlayer();
	    if (!Lists.muted.containsKey(chatter.getName())) {
	      Lists.muted.put(chatter.getName(), Boolean.valueOf(false));
	    }
	    if ((!chatter.hasPermission("globalmute.bypass")) && (
	      (Lists.muteall) || (((Boolean)Lists.muted.get(chatter.getName())).booleanValue()))) {
	      event.setCancelled(true);
	      String talk_while_chat_muted = StringUtils.translate(plugin.messages.getString("talk_while_chat_muted"));
	      talk_while_chat_muted = talk_while_chat_muted.replace("%player", event.getPlayer().getName());
	      chatter.sendMessage(talk_while_chat_muted);
	    }
	  }
	  
	  @EventHandler
	  public void ForcedGrammar(AsyncPlayerChatEvent event) {
		  Player player = event.getPlayer();
		  String message = event.getMessage();
		  
		  if (Lists.forcedgrammar.contains(player.getName())) {
		  event.setMessage(StringUtils.capitalise(StringUtils.endSentence(message)));
	  }  
	  }
	  
	  @EventHandler
	  public void DisablePluginViewing(PlayerCommandPreprocessEvent event) {
		  String[] disabledStrings= {
			"plugins", "pl", "a", "about", "?", "ver", "bukkit:ver", "bukkit:pl", "bukkit:plugin", "bukkit:a", "bukkit:?"	  
		  };
		  if (!event.getPlayer().isOp()) {
		  for (String disabledString : disabledStrings) {
			  if (event.getMessage().equalsIgnoreCase("/" + disabledString)) {
				  event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to view the plugins.");
				  event.setCancelled(true);
			  }
		  }
		  }
	  }
	  
	  @EventHandler
		public void PlayerSpamEvent(AsyncPlayerChatEvent event) {
			if (!(event.getPlayer().hasPermission("spam.bypass"))) {
				SpamManager.OnPlayerChat(event);
			}
		}
	  
	  @EventHandler
	  public void SetChatColorEvent(AsyncPlayerChatEvent event) {
		  Player player = event.getPlayer();
		  if (Lists.chatcolor.containsKey(player.getName())) {
			  event.setMessage(Lists.chatcolor.get(player.getName()).toString() + event.getMessage());
		  }
	  }
	  
	  
	  @EventHandler
	  public void AllowedChatColor(AsyncPlayerChatEvent event) {
			if (event.getPlayer().hasPermission("chatcolor.use") || event.getPlayer().hasPermission("color.yes")) {
				String message = event.getMessage();
				event.setMessage(StringUtils.colorize(message));
			}
		}
	  
	  @EventHandler  
	  public void onPlayerCalledEvent(AsyncPlayerChatEvent e) {
	  for(Player target : Bukkit.getOnlinePlayers()) {
		  if (Lists.namesound.contains(target.getName())) {
	        String[] words = e.getMessage().split(" ");
	        for(String s : words) {
	                if(s.equalsIgnoreCase(target.getName())) {
	                        target.playSound(target.getLocation(), Sound.ANVIL_LAND, 1, 1);
	                        return;
	                }
	        }
	}
	  }
	  }
	  
	  @EventHandler
	    public void SlowChatEvent(final AsyncPlayerChatEvent event) {
		  if (!event.getPlayer().isOp() || !event.getPlayer().hasPermission("slowchat.bypass")) {
		  if (Lists.slowchat == true) {
	        long now = System.currentTimeMillis();
	        String name = event.getPlayer().getName();
	        Long lastChat = Lists.slowtime.get(event.getPlayer().getName());

	        if(lastChat != null) {
	            long earliestNext = lastChat + Lists.slowtime.get("slowchattime") * 1000;
	            if(now < earliestNext) { 
	                int timeRemaining = (int)((earliestNext - now) / 1000) + 1;
	                event.getPlayer().sendMessage(ChatColor.RED + "You can not talk for " + ChatColor.BOLD + timeRemaining + ChatColor.RED  + " more second" + (timeRemaining > 1 ? "s." : "."));
	                event.setCancelled(true);
	                return;
	            }
	        }
	        Lists.slowtime.put(name, now);
	    }
		  }
	  }
	  
	  @EventHandler
	  public void SpeakInStaffChat(AsyncPlayerChatEvent e) {
	   if (Lists.staffchat.contains(e.getPlayer().getName())) {
	    e.setCancelled(true);
	    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
	     if (p.hasPermission("staffchat.yes")) p.sendMessage(ChatColor.AQUA + e.getPlayer().getName() + ": " + e.getMessage());
	    }
	   }
	  }

}
