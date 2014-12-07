package me.technopvp.common.commands;

import java.util.Random;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.enums.Permissions;
import me.technopvp.common.utilities.enums.Source;
import me.technopvp.common.utilities.enums.SourceType;
import me.technopvp.common.utilities.enums.Permissions.Permission;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_flipcoin extends CommonCommand {
	dCommon plugin = dCommon.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		
		 Random rnd = new Random();
 		   int rndstuffs;

 		for(int counter = 5; counter <= 5; counter++) {
 			rndstuffs = 1+rnd.nextInt(2);

 			if(rndstuffs == 1) {
 				player.sendMessage("§7Your coin landed on §cHeads!");
 			} else if (rndstuffs == 2) {
 				player.sendMessage("§7Your coin landed on §cTails!");
 			}
 		}
		return true;
	}
}