package net.dylem.test_mod.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	/*
     * Evènements côté client, dans la phase preInit
     * @Param event L'évènement
     */
	@Override
	public void preInit(FMLPreInitializationEvent event) {
    	
		super.preInit(event);
    }
    
	/*
     * Evènements côté client, dans la phase init
     * @Param event L'évènement
     */
	@Override
    public void init(FMLInitializationEvent event) {
    	
		super.init(event);
    }
    
	/*
     * Evènements côté client, dans la phase postInit
     * @Param event L'évènement
     */
	@Override
    public void postInit(FMLPostInitializationEvent event) {
    	
		super.postInit(event);
    }
}
