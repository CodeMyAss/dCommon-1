package me.technopvp.common.commands;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.technopvp.common.dCommon;
import me.technopvp.common.enums.Permissions;
import me.technopvp.common.enums.Source;
import me.technopvp.common.enums.SourceType;
import me.technopvp.common.enums.Permissions.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_ping extends CommonCommand {
	dCommon plugin = dCommon.instance;
	
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;
		if(args.length == 0) {
			String ping = null;
			  for (Method meth : player.getClass().getMethods()) {          
               if (meth.getName().equals("getHandle")) {      
                       try {          
                               Object obj = meth.invoke(player, (Object[])null);            
                               for (Field field : obj.getClass().getFields())                
                                       if (field.getName().equals("ping"))                  
                                               ping = String.valueOf(field.getInt(obj));          
                       }          
                       catch (Exception e)        
                       {
                     	  e.printStackTrace();          
                       }          
               }      
       }
       sender.sendMessage(ChatColor.GRAY + "Your ping is: " + ChatColor.DARK_GREEN + ping + ChatColor.GRAY + " ms");    
}   

	 if (args.length > 0) {    
      @SuppressWarnings("deprecation")
	Player all = Bukkit.getPlayer(args[0]);    
      if (all != null) {      
              String ping = null;      
              for (Method meth : all.getClass().getMethods()) {      
                      if (meth.getName().equals("getHandle")) {  
                              try {      
                                      Object obj = meth.invoke(player, (Object[])null);        
                                      for (Field field : obj.getClass().getFields())        
                                              if (field.getName().equals("ping"))            
                                                      ping = String.valueOf(field.getInt(obj));

                              }          
                              catch (Exception e) {            
                                      e.printStackTrace();            
                              }            
                      }          
              }

              sender.sendMessage(ChatColor.GRAY + all.getName() + "'s ping is: " + ChatColor.DARK_GREEN + ping + ChatColor.GRAY + " ms");      
      } else {        
              sender.sendMessage(ChatColor.RED + "Player not found.");

      }      
       
	 }
		return true;		
}
}
