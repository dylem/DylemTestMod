package net.dylem.test_mod.proxy;

import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	/*
     * Ev�nements c�t� serveur, dans la phase preInit
     * @Param event L'�v�nement
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    }
    
    /*
     * Ev�nements c�t� serveur, dans la phase init
     * @Param event L'�v�nement
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    }
    
    /*
     * Ev�nements c�t� serveur, dans la phase postInit
     * @Param event L'�v�nement
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
}
