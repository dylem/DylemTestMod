package net.dylem.test_mod.item;

import net.dylem.test_mod.util.IVariant;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;

/*
 * Lié à ItemVariants, contenant ses variantes
 */
public enum EnumItemVariants implements IVariant.IEnumVariant {
	
	// Le nom correspond à la deuxième partie de l'unlocalized name de la variante
	A(0, "a"),
	B(1, "b"),
	C(2, "c");
	
	private static final EnumItemVariants[] META_LOOKUP = new EnumItemVariants[values().length];
	private final int meta;
    private final String name;
    
    /*
     * Crée une nouvelle variante
     * @Param metaIn la meta de la variante
     * @Param nameIn le nom
     * @Param chatColorIn la couleur du texte
     */
	private EnumItemVariants(int metaIn, String nameIn) {
		
		this.meta = metaIn;
		this.name = nameIn;
    } 
	
	/*
	 * Retourne la meta de la variante
	 * @return meta
	 */
	@Override
	public int getMeta() {
		
		return meta;
	}
	
	/*
	 * Retourne le nom de la variante
	 * @return name
	 */
	@Override
	public String getName() {

		return name;
	}
	
	/* 
	 * Retourne la variante (l'unlocalized name) correspondant à la meta
	 * @Param la meta à chercher
	 * @return A, B ou C en fonction de la meta
	 */
	public static EnumItemVariants byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }
	
	 static {
	        for (EnumItemVariants enumType : values()) {
	            META_LOOKUP[enumType.getMeta()] = enumType;
	        }
	    }
}