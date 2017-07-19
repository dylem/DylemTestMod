package net.dylem.test_mod.item;

import net.dylem.test_mod.util.IVariant;
import net.minecraft.util.text.TextFormatting;

public enum EnumSoulSword implements IVariant.IEnumVariant {

	// Le nom correspond à la deuxième partie de l'unlocalized name de la variante
	COMMON(0, "common", TextFormatting.WHITE),
	RARE(1, "rare", TextFormatting.BLUE),
	EPIC(2, "epic", TextFormatting.DARK_PURPLE),
	LEGENDARY(3, "legendary", TextFormatting.YELLOW);
	
	private static final EnumSoulSword[] META_LOOKUP = new EnumSoulSword[values().length];
	private final int meta;
    private final String name;
    private final TextFormatting chatColor;
    
    /*
     * Crée une nouvelle variante
     * @Param metaIn la meta de la variante
     * @Param nameIn le nom
     * @Param chatColorIn la couleur du texte
     */
	private EnumSoulSword(int metaIn, String nameIn, TextFormatting chatColorIn) {
		
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
	public static EnumSoulSword byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }
	
	 static {
	        for (EnumSoulSword enumType : values()) {
	            META_LOOKUP[enumType.getMeta()] = enumType;
	        }
	    }

}
