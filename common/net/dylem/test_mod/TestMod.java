package net.dylem.test_mod;

import java.util.ArrayList;
import java.util.List;

import net.dylem.test_mod.init.ModItems;
import net.dylem.test_mod.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/*
 * Classe g�n�rique du mod
 */
@Mod(modid = TestMod.MOD_ID, name = TestMod.MOD_NAME, version = TestMod.VERSION)
public class TestMod {
	
	public static final String MOD_ID = "test_mod";
	public static final String MOD_NAME = "Dylem's Test Mod";
	public static final String RESOURCE_PREFIX = "test_mod:";
    public static final String VERSION = "0.1";
    
    // Onglet en mode cr�atif, comportant tous les items du mod
    public static final CreativeTabs TEST_TAB = new CreativeTabs(MOD_ID) {
    	
    	/*
    	 * Permet de r�cup�rer l'icone de la CreativeTab
    	 * @see net.minecraft.creativetab.CreativeTabs#getTabIconItem()
    	 */
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.ITEM_BASIC);
		}	
    };
    
    // Permet l'instanciation des annotations @Mod
    @Instance(MOD_ID)
    public static TestMod Instance;
    
    // Permet l'instanciation des proxys
    @SidedProxy(clientSide = "net.dylem.test_mod.proxy.ClientProxy", serverSide = "net.dylem.test_mod.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    /*
     * Ev�nements c�t� serveur, dans la phase preInit
     * @Param event L'�v�nement
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	proxy.preInit(event);
    }
    
    /*
     * Ev�nements c�t� serveur, dans la phase init
     * @Param event L'�v�nement
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    	proxy.init(event);
    }
    
    /*
     * Ev�nements c�t� serveur, dans la phase postInit
     * @Param event L'�v�nement
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    	proxy.postInit(event);
    }
    
}
