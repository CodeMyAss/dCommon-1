package me.technopvp.common.utilities.config;

import java.io.File;
import java.io.IOException;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.CommonCore;

public class MOTDConfig extends CommonCore {
    dCommon plugin = dCommon.instance;
    
    private MOTDConfig() { }
    
    static MOTDConfig instance = new MOTDConfig();
   
    public static MOTDConfig getInstance() {
            return instance;
    }
    
    private File motdFile = null;
    
    public void generateMOTDConfig() {
        if (motdFile == null) {
            motdFile = new File(plugin.getDataFolder(), "motd.txt");
            try {
                motdFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}