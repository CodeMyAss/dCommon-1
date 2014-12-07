package me.technopvp.common.integration;

import me.technopvp.common.dCommon;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultBridge {
	dCommon plugin = dCommon.instance;
	
	static VaultBridge instance = new VaultBridge();
	
	public static VaultBridge getInstance() {
		return instance;
	}

    public boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = Bukkit.getServicesManager().getRegistration(Permission.class);
        if (permissionProvider != null) {
        	plugin.permission = permissionProvider.getProvider();
        }
        return (plugin.permission != null);
    }

    public boolean setupChat()
    {
        RegisteredServiceProvider<Chat> chatProvider = Bukkit.getServicesManager().getRegistration(Chat.class);
        if (chatProvider != null) {
        	plugin.chat = chatProvider.getProvider();
        }

        return (plugin.chat != null);
    }

    public boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
        	plugin.economy = economyProvider.getProvider();
        }

        return (plugin.economy != null);
    }
    
    public boolean setupAll(boolean permissions, boolean chat, boolean economy) { 
    	if (permissions == true) {
    		setupPermissions();
    	}
    	if (chat == true) {
    		setupChat();
    	}
    	if (economy == true) {
    		setupEconomy();
    	}
    	return true;
    }
    
    
    

}
