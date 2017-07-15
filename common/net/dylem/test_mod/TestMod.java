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

@Mod(modid = TestMod.MOD_ID, name = TestMod.MOD_NAME, version = TestMod.VERSION)
public class TestMod {
	
	public static final String MOD_ID = "test_mod";
	public static final String MOD_NAME = "Dylem's Test Mod";
	public static final String RESOURCE_PREFIX = "test_mod:";
    public static final String VERSION = "0.1";
    
    public static final CreativeTabs TEST_TAB = new CreativeTabs(MOD_ID) {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.ITEM_BASIC);
		}
    	
    };
    
    @Instance(MOD_ID)
    public static TestMod Instance;
    
    @SidedProxy(clientSide = "net.dylem.test_mod.proxy.ClientProxy", serverSide = "net.dylem.test_mod.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    	proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    	proxy.postInit(event);
    }
    
}
