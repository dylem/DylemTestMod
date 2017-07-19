package net.dylem.test_mod.item;

import net.dylem.test_mod.util.IVariant;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;

/*
 * Li� � ItemVariants, contenant ses variantes
 */
public enum EnumItemVariants implements IVariant.IEnumVariant {
	
	// Le nom correspond � la deuxi�me partie de l'unlocalized name de la variante
	A(0, "a"),
	B(1, "b"),
	C(2, "c");
	
	private static final EnumItemVariants[] META_LOOKUP = new EnumItemVariants[values().length];
	private final int meta;
    private final String name;
    
    /*
     * Cr�e une nouvelle variante
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
	 * Retourne la variante (l'unlocalized name) correspondant � la meta
	 * @Param la meta � chercher
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