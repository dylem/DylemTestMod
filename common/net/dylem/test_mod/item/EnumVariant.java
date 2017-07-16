package net.dylem.test_mod.item;

import net.dylem.test_mod.util.IVariant;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;

/*
 * Lié à ItemVariants, contenant ses variantes
 */
public enum EnumVariant implements IVariant {
	
	// Le nom correspond à la deuxième partie de l'unlocalized name de la variante
	a(0, "a", TextFormatting.BLUE),
	b(1, "b", TextFormatting.WHITE),
	c(2, "c", TextFormatting.RED);
	
	private static final EnumVariant[] META_LOOKUP = new EnumVariant[values().length];
	private final int meta;
    private final String name;
    private final TextFormatting chatColor;
    
    /*
     * Crée une nouvelle variante
     * @Param metaIn la meta de la variante
     * @Param nameIn le nom
     * @Param chatColorIn la couleur du texte
     */
	private EnumVariant(int metaIn, String nameIn, TextFormatting chatColorIn) {
		
		this.meta = metaIn;
		this.name = nameIn;
		this.chatColor = chatColorIn;
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
	public static EnumVariant byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }
	
	 static {
	        for (EnumVariant enumType : values()) {
	            META_LOOKUP[enumType.getMeta()] = enumType;
	        }
	    }
}