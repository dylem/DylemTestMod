package net.dylem.test_mod.util;

import net.minecraft.util.IStringSerializable;

public interface IVariant {
	
	/*
	 * Classe d'aide à la création d'Enum pour les items à multiples variantes
	 */
	public interface IEnumVariant extends IStringSerializable {
		
		/*
		 * Permet de récupérer la metadata d'une variante
		 * @Return la metadata
		 */
		int getMeta();
	}
	
	/*
	 * Classe d'aide à la création d'items à multiples variantes
	 */
	public interface IItemVariant {
		
		/*
		 * Permet de récuperer les valeurs des variantes
		 * @Return le tableau comprenant les valeurs des variantes
		 */
		<T extends IEnumVariant> T[] getValues();
	}
}