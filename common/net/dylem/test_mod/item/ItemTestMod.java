package net.dylem.test_mod.item;

import net.dylem.test_mod.TestMod;
import net.minecraft.item.Item;

/*
 * Classe générique pour les items du mod
 * Tous les items du mod le pouvant étendent cette classe
 */
public class ItemTestMod extends Item {
	
	public ItemTestMod(final String itemName) {
	    
		setItemName(this, itemName);
		setCreativeTab(TestMod.TEST_TAB);
	}
	
	/*
	 * Définit le nom dans le registre
	 * Définit l'unlocalized name
	 * @Param itemName le nom de l'item
	 */
	public static void setItemName(final Item item, final String itemName) {
	    
		item.setRegistryName(TestMod.MOD_ID, itemName);
		item.setUnlocalizedName(item.getRegistryName().toString());
	}
}
