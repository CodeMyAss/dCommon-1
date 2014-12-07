package me.technopvp.common;

import java.util.logging.Logger;

import me.technopvp.common.api.GhostManager;
import me.technopvp.common.commands.CommonCommand;
import me.technopvp.common.managers.InitializeManager;
import me.technopvp.common.utilities.Utils;
import me.technopvp.common.utilities.enums.Level;
import me.technopvp.common.utilities.enums.Permissions.PermissionUtils;
import me.technopvp.common.utilities.enums.Source.SourceUtils;
import me.technopvp.common.utilities.player.UserManager;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class dCommon extends JavaPlugin {

	/*
	 * Information:
	 * Started: 6/28/2013
	 * Authors: [tpvp, TechnoPvP, SimpleCode]
	 * Last Edited: 11/22/2014
	 */

	/*
	 * TODO LIST:
	 * - Fish Enchanting Command.
	 * - Add Warps Folder/File.
	 * - Add '/tpa', '/tpahere' command.
	 * - Add '/give', '/i' command.
	 */

	 public String error = "§cInvalid Arguments", other = "§7You do not have permission to perfom this command on others.";
	 public String imput = "§cInvalid Imput", nstaff = "§7You are not a staff member";
	 public String usage = "§cInvalid Usage", donor = "§7You must donate for this command";

	 public static dCommon instance;

	 public Logger log = Logger.getLogger("Minecraft");
	 public PluginManager pluginmanager;
	 public UserManager user;

	 public GhostManager ghostManager;
	 public FileConfiguration messages;
	 public InitializeManager initializeManager;

	 public Permission permission = null;
	 public Economy economy = null;
	 public Chat chat = null;

  public void onEnable() {
	  instance = this;
	  initializeManager = new InitializeManager();

	  Utils.log(Level.HIGH, "[" + this.getName() + "]" + " version " + this.getDescription().getVersion() + " has succesfully been enabled.");
	  Utils.log(Level.MEDIUM, "[" + this.getName() + "]" + " Initialization class files have been loaded \nsuccesfully no erros found.");
  }

	public void onDisable() {
		log.info("[" + this.getName() + "] Plugin has been disabled, all files saved.");
	}

	    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	        final CommonCommand dispatcher;

	        /* Load the initialize class */
	        try {
	            ClassLoader classLoader = dCommon.class.getClassLoader();
	            dispatcher = (CommonCommand) classLoader.loadClass(String.format("%s.%s", CommonCommand.class.getPackage().getName(), "Command_" + cmd.getName().toLowerCase())).newInstance();
	            dispatcher.setCommandSender(sender);
	        } catch (Exception e) {
	            log.severe("Command not loaded: " + cmd.getName());
	            log.severe(e.toString());
	            sender.sendMessage(ChatColor.RED + "Command Error: Command not loaded: " + cmd.getName());
	            return true;
	        }

	        /* Check for permissions */
	        try {
	            if (!SourceUtils.fromSource(sender, dispatcher.getClass(), this)) {
	                return (sender instanceof Player ? dispatcher.consoleOnly() : dispatcher.playerOnly());
	            }

	            if (PermissionUtils.hasPermission(sender, dispatcher.getClass(), this)) {
	                return dispatcher.run(sender, cmd, args);
	            } else {
	                return dispatcher.noPermission();
	            }
	        } catch (Exception e) {
	            Utils.log(Level.HIGH, "Unknown command error: " + e.getMessage());
	            Utils.log(Level.HIGH, e.toString());
	            sender.sendMessage(ChatColor.RED + "Command Error: " + e.getMessage());
	            e.printStackTrace();
	            return true;
		}
	}
}
