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
	    
		setItemName(this, itemName);
		setCreativeTab(TestMod.TEST_TAB);
	}
	
	/*
	 * Définit le nom dans le registre
	 * Définit l'unlocalized name
	 * @Param item L'item, de sorte que la fonction puisse être utilisée par
	 * des items qui n'étendent pas ItemTestMod
	 * @Param itemName le nom de l'item
	 */
	static void setItemName(final Item item, final String itemName) {
	    
		item.setRegistryName(TestMod.MOD_ID, itemName);
		item.setUnlocalizedName(item.getRegistryName().toString());
	}
}
