package net.dylem.test_mod.item;

import net.dylem.test_mod.TestMod;
import net.dylem.test_mod.util.IVariant;
import net.minecraft.item.Item;

/*
 * Classe g�n�rique pour les items du mod
 * Tous les items du mod �tendent cette classe
 */
public class ItemTestMod extends Item {
	
	public ItemTestMod(final String itemName) {
	    
		setItemName(itemName);
		setCreativeTab(TestMod.TEST_TAB);
	}
	
	/*
	 * D�finit le nom dans le registre
	 * D�finit l'unlocalized name
	 * @Param itemName le nom de l'item
	 */
	private void setItemName(final String itemName) {
	    
		setRegistryName(TestMod.MOD_ID, itemName);
		setUnlocalizedName(getRegistryName().toString());
	}
}
