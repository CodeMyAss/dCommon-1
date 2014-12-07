package me.technopvp.common.utilities;

import java.util.Random;

import me.technopvp.common.dCommon;
import me.technopvp.common.managers.MessageManager;
import me.technopvp.common.utilities.enums.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class StringUtils {
	static dCommon plugin = dCommon.instance;

	public static String format(String string) {
		string = StringUtils.endSentence(string);
		string = StringUtils.capitalise(string);
		string = StringUtils.colorize(string);
		string = translate(string);

		return string;
	}

	public static String removeUnderscore(String string) {
		string = string.replaceAll("_", "");
		return string;
	}

	public static boolean clearchat() {
		for (int i = 0; i < 200; i++) {
			Bukkit.broadcastMessage("");
		}
		return true;
	}

	public static boolean clearchat(int amount) {
		for (int i = 0; i < amount; i++) {
			Bukkit.broadcastMessage("");
		}
		return true;
	}

	public static String colorize(String string) {
		string = string.replace("&", "§");
		return string;
	}

	public boolean capsCheck(String s) {
		s = s.replaceAll("[^a-zA-Z]", "");
		if (s.isEmpty()) return false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!Character.isUpperCase(c)) return false;
		}
		return true;
	}

	public static String getMessage(String[] args, int start) {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = start; i < args.length; i++) {
			if (i != start) {
				stringBuilder.append(" ");
			}

			stringBuilder.append(args[i]);
		}

		return stringBuilder.toString();
	}

	public static String capitalise(String message) {
		String LINK = "(?i)(https?:\\/\\/)?([\\da-z\\.\\-]+)\\.([a-z]{2,6})([\\/\\w\\.\\-\\?=&]*)";
		String sentenceDelimiter = "(?<=[!?\\.])\\s";
		String[] sentences = message.split(sentenceDelimiter);
		StringBuilder capitalisedMessage = new StringBuilder();

		for (String sentence : sentences) {
			String firstWord = sentence.split("\\s")[0];
			if (!firstWord.matches(LINK)) {
				sentence = StringUtils.capitalise(sentence);
			}
			capitalisedMessage.append(sentence).append(" ");
		}

		return capitalisedMessage.toString().trim();
	}

	public static String translate(String string) {
		if (string == null) {
			MessageManager.log(Level.MEDIUM, "Messges.yml file is missing a string.");
			return " This message is has no object.";
		}
		string = string.replace("<none>", "");
		string = string.replace("%server", StringUtils.getServerName());
		string = string.replace("%donatepage", StringUtils.getDonationPage());
		string = string.replace("%website", StringUtils.getWebsite());
		string = string.replace("&", "§");
		string = string.replace("^", "'");
		return string;
	}

	public static String prefix() {
		return "[dCommon] ";
	}

	public static String rainbowChatColor(String string) {
		int lastColor = 0;
		int currColor = 0;
		String newMessage = "";
		String colors = "123456789abcde";
		for (int i = 0; i < string.length(); i++) {
			do {
				currColor = new Random().nextInt(colors.length() - 1) + 1;
			} while (currColor == lastColor);

			newMessage += ChatColor.RESET.toString() + ChatColor.getByChar(colors.charAt(currColor)) + "" + string.charAt(i);

		}
		return newMessage;
	}

	public static String endSentence(String message) {
		String LINK = "(?i)(https?:\\/\\/)?([\\da-z\\.\\-]+)\\.([a-z]{2,6})([\\/\\w\\.\\-\\?=&]*)";
		String lastChar = message.substring(message.length() - 1);
		String[] words = message.split("\\s");
		String lastWord = words[words.length - 1];

		// Cases for not adding a full-stop.
		// Last character is not a letter.
		if (!lastChar.matches("(?i)[a-z]")) return message;
		// Last word is a link.
		if (lastWord.matches(LINK)) return message;
		// Message ends with smiley.
		if (message.matches("^.*[X:;]D$")) return message;

		return message + ".";
	}

	public static String permission() {
		String permission = translate(plugin.messages.getString("no_permission"));
		return permission;
	}

	public static String getDonationPage() {
		return plugin.getConfig().getString("donation-page");
	}

	public static String getWebsite() {
		return plugin.getConfig().getString("website");
	}

	public static String getServerName() {
		return plugin.getConfig().getString("server-name");
	}

}
