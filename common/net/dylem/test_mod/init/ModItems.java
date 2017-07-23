package net.dylem.test_mod.init;

import java.util.HashSet;

import net.dylem.test_mod.item.ItemSoulSword;
import net.dylem.test_mod.item.ItemTeleportationTotem;
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
	
	// Item de base ne faisant rien, avec un joli Kappa comme texture
	public static final ItemTestMod ITEM_BASIC = new ItemTestMod("item_basic");
	
	// Un totem qui permet de se t�l�porter � une position enregistr�e
	public static final ItemTeleportationTotem TELEPORTATION_TOTEM = new ItemTeleportationTotem("teleportation_totem");
	
	// Item � trois variantes, les lettres A, B et C
	public static final ItemVariants ITEM_VARIANTS = new ItemVariants("item_variants");
	
	// Une �p�e qui garde les ames des �nemis en m�moire, la rendant plus puissante
	public static final ItemSoulSword SOUL_SWORD = new ItemSoulSword(Item.ToolMaterial.DIAMOND, "soul_sword");
	
	/*
	 * Contient tous les items enregistr�s
	 * HashSet -> pas de doublons
	 */
	public static final HashSet<Item> REGISTERED_ITEMS = new HashSet();
	
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
			final Item[] items = {
				ITEM_BASIC,	
				TELEPORTATION_TOTEM,
				ITEM_VARIANTS,
				SOUL_SWORD,
			};
			
			final IForgeRegistry<Item> registry = event.getRegistry();
			
			/*
			 * Pour chaque item, on l'enregistre � l'aide de l'�v�nement
			 * Puis on l'ajoute au set d'item enregistr�s, pour plus tard enregistrer son mod�le
			 */
			for(final Item item : items) {
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
			
			REGISTERED_ITEMS.forEach(item -> { // Pour chaque Item enregistr�, ..
				
				final String modelLocation = item.getRegistryName().toString();
				
				// S'il poss�de des variantes
				if(item instanceof IVariant.IItemVariant) {
					
					IVariant.IEnumVariant values[] = ((IVariant.IItemVariant)item).getValues();
					
					if(values != null) {
						INSTANCE.registerVariantItemModels(item, modelLocation, values);
					}
				}
				else {
					
					final ModelResourceLocation fullModelLocation = new ModelResourceLocation(modelLocation, "inventory");
			        INSTANCE.registerItemModel(item, fullModelLocation);
				}
			});
		}
		
		/*
		 * Enregistre le mod�le d'un item
		 * Etape 2 : Permet de r�cup�rer la meshDefinition de l'item
		 * @Param item L'item
		 * @Param modelLocation la localisation compl�te de l'item
		 */
		private void registerItemModel(final Item item, final ModelResourceLocation fullModelLocation) {
			
			ModelBakery.registerItemVariants(item, fullModelLocation);
			registerItemModel(item, MeshDefinitionFix.create(stack -> fullModelLocation));
		}
		
		/*
		 * Enregistre le mod�le d'un item
		 * @Param item L'item
		 * @Param meshDefinition la meshDefinition de l'item
		 */
		private void registerItemModel(final Item item, final ItemMeshDefinition meshDefinition) {
			
		    ModelLoader.setCustomMeshDefinition(item, meshDefinition);
		}
		
		/*
		 * Enregistre les mod�les d'un item avec variantes
		 * @Param item L'item
		 * @Param modelLocation le nom de l'item dans le registre
		 * @Param values les variantes de l'item
		 */
		private <T extends IVariant.IEnumVariant> void registerVariantItemModels(final Item item, final String modelLocation, final T[] values) {
			
			for (final T value : values) {
				registerItemModelForMeta(item, value.getMeta(), modelLocation + "=" + value.getName());
			}
		}
		
		/*
		 * Rengistre la variante d'un item
		 * @Param item L'item
		 * @Param metadata la meta de la variante
		 * @Param variantLocation la localisation de la variante
		 */
		private void registerItemModelForMeta(final Item item, final int metadata, final String variantLocation) {
			
		    ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), variantLocation);
		    ModelLoader.setCustomModelResourceLocation(item, metadata, location);
		}
	}
}
