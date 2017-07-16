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
		setUnlocalizedName(getRegistryName().toString());
	}
}
