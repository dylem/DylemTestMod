package net.dylem.test_mod.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	/*
     * Ev�nements c�t� client, dans la phase preInit
     * @Param event L'�v�nement
     */
	@Override
	public void preInit(FMLPreInitializationEvent event) {
    	
		super.preInit(event);
    }
    
	/*
     * Ev�nements c�t� client, dans la phase init
     * @Param event L'�v�nement
     */
	@Override
    public void init(FMLInitializationEvent event) {
    	
		super.init(event);
    }
    
	/*
     * Ev�nements c�t� client, dans la phase postInit
     * @Param event L'�v�nement
     */
	@Override
    public void postInit(FMLPostInitializationEvent event) {
    	
		super.postInit(event);
    }
}
