package net.dylem.test_mod.util;

import net.minecraft.util.IStringSerializable;

public interface IVariant {
	
	/*
	 * Classe d'aide � la cr�ation d'Enum pour les items � multiples variantes
	 */
	public interface IEnumVariant extends IStringSerializable {
		
		/*
		 * Permet de r�cup�rer la metadata d'une variante
		 * @Return la metadata
		 */
		int getMeta();
	}
	
	/*
	 * Classe d'aide � la cr�ation d'items � multiples variantes
	 */
	public interface IItemVariant {
		
		/*
		 * Permet de r�cuperer les valeurs des variantes
		 * @Return le tableau comprenant les valeurs des variantes
		 */
		<T extends IEnumVariant> T[] getValues();
	}
}