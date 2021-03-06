package net.subaraki.telepads.handler;

import java.io.File;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import net.subaraki.telepads.util.Constants;

public class ConfigurationHandler {
    
    /**
     * Local instance of the Configuration.
     */
    public static Configuration config;
    
    /**
     * A configurable flag that determines whether or not the user wants to receive debug
     * messages from the Telepads mod.
     */
    public static boolean allowDebugMessages = true;
    
    /**
     * A configurable flag that determines whether or not the Telepad will spawn particle
     * effects.
     */
    public static boolean allowParticleEffects = true;
    
    /**
     * A configurable string that determines the type of particle that is spawned by the
     * Telepad.
     */
    public static String particleName = "portal";
    
    /**
     * A configurable flag that determines wether the ender dragon prevents the player from
     * leaving the end on a telepad.
     */
    public static boolean allowDragonBlockingEndExit = true;
    
    /**
     * A configurable flag that determines wether the transmitter gets placed randomly in one
     * of four corners or will always be placed in the same corner.
     */
    public static boolean allowRandomCorneredTransmitter = true;
    
    /**
     * Constructs the ConfigurationHandler. Only one ConfigurationHandler instance should ever
     * be created.
     * 
     * @param configFile : The file to use for reading/writing configuration data.
     */
    public ConfigurationHandler(File configFile) {
        
        config = new Configuration(configFile);
        FMLCommonHandler.instance().bus().register(this);
        syncConfigData();
    }
    
    @SubscribeEvent
    public void onConfigChange (ConfigChangedEvent.OnConfigChangedEvent event) {
        
        if (event.modID.equals(Constants.MODID))
            syncConfigData();
    }
    
    /**
     * When called, all configurable fields will be reinitialized. For internal use only.
     */
    private void syncConfigData () {
        
        // Settings
        allowDebugMessages = config.getBoolean("allowDebug", "settings", allowDebugMessages, "Determines whether or not dubug messages from the Telepads mod should be printed to the console.");
        allowParticleEffects = config.getBoolean("allowParticles", "settings", allowParticleEffects, "Should particle effects be spawned from the Telepad?");
        particleName = config.getString("particleType", "settings", particleName, "The type of particle that should spawn from the Telepad?");
        allowDragonBlockingEndExit = config.getBoolean("allowDragonBlockingEndExit", "settings", allowDragonBlockingEndExit, "Determines wether or not the prescence of the Enderdragon prevents a player from leaving the End trough a Telepad");
        allowRandomCorneredTransmitter = config.getBoolean("allowRandomCorneredTransmitter", "settings", allowRandomCorneredTransmitter, "Determines wether the transmitter gets placed randomly in one of four corners or will always be placed in the same corner.");
        
        if (config.hasChanged())
            config.save();
    }
}