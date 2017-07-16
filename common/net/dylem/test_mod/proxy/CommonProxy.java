package net.dylem.test_mod.proxy;

import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	/*
     * Evènements côté serveur, dans la phase preInit
     * @Param event L'évènement
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    }
    
    /*
     * Evènements côté serveur, dans la phase init
     * @Param event L'évènement
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    }
    
    /*
     * Evènements côté serveur, dans la phase postInit
     * @Param event L'évènement
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
}
