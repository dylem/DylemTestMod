package net.dylem.test_mod.item;

import net.dylem.test_mod.TestMod;
import net.dylem.test_mod.util.IVariant;
import net.minecraft.item.Item;

/*
 * Classe générique pour les items du mod
 * Tous les items du mod étendent cette classe
 */
public class ItemTestMod extends Item {
	
	public ItemTestMod(final String itemName) {
	    
		setItemName(itemName);
		setCreativeTab(TestMod.TEST_TAB);
	}
	
	/*
	 * Définit le nom dans le registre
	 * Définit l'unlocalized name
	 * @Param itemName le nom de l'item
	 */
	private void setItemName(final String itemName) {
	    
		setRegistryName(TestMod.MOD_ID, itemName);
		setUnlocalizedName(getStringRegistryName());
	}
	
	/*
	 * Transforme le nom dans le registre en String
	 */
	public String getStringRegistryName() {
		
		return this.getRegistryName().toString();
	}
	
	/*
	 * Permet de récuperer les valeurs pour les items avec meta
	 * Utilisé par :
	 * ModItems.ModelHandler.registerItemModel(ItemTestMod, String)
	 * @Return le tableau comprenant toutes les valeurs
	 */
	public <T extends IVariant> T[] getValues() {
		
		return null;
	}
}
