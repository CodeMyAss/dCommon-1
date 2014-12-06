package me.technopvp.common.managers;

import java.util.ArrayList;
import java.util.Hashtable;

import me.technopvp.common.utility.player.Ban;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SpamManager {
	public static Hashtable<String, SpamManager> Players = new Hashtable<String, SpamManager>();
	public ArrayList<ChatMessage> Messages;
	public String PlayerName;
	public Player Player;
	public int Warnings;
	public int Violations;
	public long SilenceExpires;

	public static void OnPlayerChat(AsyncPlayerChatEvent event) {
		String playerName = event.getPlayer().getName();
		SpamManager check = (SpamManager) Players.get(playerName);

		if (check == null) {
			check = new SpamManager(playerName);
			Players.put(playerName, check);
		}

		check.AddMessage(event);
	}

	public SpamManager(String name) {
		this.Messages = new ArrayList<ChatMessage>();
		this.PlayerName = name;
		this.Warnings = 10;
		this.Violations = 5;
		this.SilenceExpires = 0L;
	}

	public void AddMessage(AsyncPlayerChatEvent event) {
		this.Player = event.getPlayer();

		ChatMessage message = new ChatMessage(this.Player, System.currentTimeMillis(), event.getMessage());
		this.Messages.add(0, message);

		if (IsSilenced()) {
			event.setCancelled(true);

			if (this.Warnings <= 0) {
				new Ban(PlayerName, "Spamming", "Console");
				return;
			}

			this.Player.sendMessage(ChatColor.RED + "You have been muted for spam! " + ChatColor.GREEN + ChatColor.BOLD + SilenceTimeRemaining() + ChatColor.RED + " remaining.");
			this.Player.sendMessage(ChatColor.RED + "If you continue spamming, you will be " + ChatColor.RED + ChatColor.BOLD + "banned!");
			this.Player.sendMessage(ChatColor.RED + "You have " + ChatColor.GREEN + ChatColor.BOLD + this.Warnings + ChatColor.RED + " warnings!");

			this.Warnings -= 1;
		} else {
			this.Warnings = 10;
			CheckSpamming();
		}

		if (this.Messages.size() > 10) {
			this.Messages.remove(this.Messages.size() - 1);
		}
	}

	public void CheckSpamming() {
		boolean isSpamming = false;
		if (this.Messages.size() >= 3) {
			String msg = ((ChatMessage) this.Messages.get(0)).message;
			boolean same = true;
			for (int i = 1; i < 3; i++) {
				same = (same) && (msg.equals(((ChatMessage) this.Messages.get(i)).message));
			}

			long time = ((ChatMessage) this.Messages.get(0)).when - ((ChatMessage) this.Messages.get(2)).when;

			isSpamming = (isSpamming) || (time < 1550L);

			isSpamming = (isSpamming) || ((time < 5000L) && (same));
		}

		if (isSpamming) {
			Silence(60);
			this.Warnings -= 1;
		}
	}

	public void Silence(int seconds) {
		this.Violations -= 1;
		this.SilenceExpires = (System.currentTimeMillis() + seconds * 1000);
		this.Player.sendMessage(ChatColor.RED + "You are muted for "  + ChatColor.GREEN + ChatColor.BOLD + seconds + ChatColor.RED + " seconds");
		this.Player.sendMessage(ChatColor.RED + "If you still spam, you will be" + ChatColor.GREEN + ChatColor.BOLD + " BANNED" + ChatColor.RED + "!");
	}

	public String SilenceTimeRemaining() {
		long left = (this.SilenceExpires - System.currentTimeMillis()) / 1000L;

		if (left < 60L) {
			return left + " seconds";
		}
		return left + " minutes";
	}

	public boolean IsSilenced() {
		return this.SilenceExpires > System.currentTimeMillis();
	}

	public void Clear() {
		this.Messages.clear();
	}

	public class ChatMessage {
		public Player player;
		public long when;
		public String message;

		public ChatMessage(Player p, long w, String msg) {
			this.player = p;
			this.when = w;
			this.message = msg;
		}
	}
}