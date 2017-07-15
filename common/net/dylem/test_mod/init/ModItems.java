package net.dylem.test_mod.init;

import java.util.HashSet;

import net.dylem.test_mod.item.ItemBasic;
import net.dylem.test_mod.item.ItemTestMod;
import net.dylem.test_mod.item.ItemVariants;
import net.dylem.test_mod.util.IVariant;
import net.dylem.test_mod.util.client.renderer.MeshDefinitionFix;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistry;

/* 
 * D�clare et instancie les items du mod
 */
public class ModItems {
	
	public static final ItemBasic ITEM_BASIC = new ItemBasic("item_basic");
	public static final ItemVariants ITEM_VARIANTS = new ItemVariants("item_variants");
	
	/*
	 * Contient tous les items enregistr�s
	 * HashSet -> pas de doublons
	 */
	public static final HashSet<ItemTestMod> REGISTERED_ITEMS = new HashSet();
	
	/* 
	 * Enregistre les items
	 * Classe automatiquement abonn�e � MinecraftForge.EVENT_BUS
	 */
	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		
		/* 
		 * Enregistre les items
		 * @param event L'�v�nement
		 */
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			
			// Contient tous les items du mod, pas encore enregistr�s
			final ItemTestMod[] items = {
				ITEM_BASIC,	
				ITEM_VARIANTS,
			};
			
			final IForgeRegistry<Item> registry = event.getRegistry();
			
			/*
			 * Pour chaque item, on l'enregistre � l'aide de l'�v�nement
			 * Puis on l'ajoute au set d'item enregistr�s, pour plus tard enregistrer son mod�le
			 */
			for(final ItemTestMod item : items) {
				registry.register(item);
				REGISTERED_ITEMS.add(item);
			}
		}
	}
	
	/*
	 * Enregistre les mod�les des items
	 * Classe automatiquement abonn�e � MinecraftForge.EVENT_BUS c�t� CLIENT
	 */
	@Mod.EventBusSubscriber(Side.CLIENT)
	public static class ModelHandler {
		
		private static final ModelHandler INSTANCE = new ModelHandler();
		 
		/*
		 * Enregistre les mod�les des items
		 * @Param event L'�v�nement
		 */
		@SubscribeEvent
		public static void registerItemModels(final ModelRegistryEvent event) {
			
			REGISTERED_ITEMS.forEach(item -> INSTANCE.registerItemModel(item, item.getStringRegistryName()));
		}
		
		/*
		 * Enregistre le mod�le d'un item
		 * Etape 1 : Transforme le param�tre modelLocation en localisation compl�te de l'item
		 * @Param item L'item
		 * @Param modelLocation Le nom de l'item dans le registre
		 */
		private void registerItemModel(final ItemTestMod item, final String modelLocation) {
			
			if(item.getValues() != null) {
				registerVariantItemModels(item, modelLocation, item.getValues());
			}
			else {
				final ModelResourceLocation fullModelLocation = new ModelResourceLocation(modelLocation, "inventory");
		        registerItemModel(item, fullModelLocation);
			}
		}
		
		/*
		 * Enregistre le mod�le d'un item
		 * Etape 2 : Permet de r�cup�rer la meshDefinition de l'item
		 * @Param item L'item
		 * @Param modelLocation la localisation compl�te de l'item
		 */
		private void registerItemModel(final ItemTestMod item, final ModelResourceLocation fullModelLocation) {
			
			ModelBakery.registerItemVariants(item, fullModelLocation);
			registerItemModel(item, MeshDefinitionFix.create(stack -> fullModelLocation));
		}
		
		/*
		 * Enregistre le mod�le d'un item
		 * @Param item L'item
		 * @Param meshDefinition la meshDefinition de l'item
		 */
		private void registerItemModel(final ItemTestMod item, final ItemMeshDefinition meshDefinition) {
			
	        ModelLoader.setCustomMeshDefinition(item, meshDefinition);
		}
		
		private <T extends IVariant> void registerVariantItemModels(final Item item, final String variantName, final T[] values) {
			
			for (final T value : values) {
				registerItemModelForMeta(item, value.getMeta(), variantName + "_" + value.getName());
			}
		}

		
		private void registerItemModelForMeta(final Item item, final int metadata, final String variant) {
			
	        registerItemModelForMeta(item, metadata, new ModelResourceLocation(item.getRegistryName(), variant));
	    }
	    private void registerItemModelForMeta(final Item item, final int metadata, final ModelResourceLocation modelResourceLocation) {
	    	
	        ModelLoader.setCustomModelResourceLocation(item, metadata, modelResourceLocation);
	    }
	}
}
