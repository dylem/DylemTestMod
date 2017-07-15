package net.dylem.test_mod.item;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * item avec plusieurs variantes
 */
public class ItemVariants extends ItemTestMod {
	
	public ItemVariants(final String itemName) {
		
		super(itemName);
	}
	
	/*
	 * Retourne l'unlocalized name correspondant à la variante
	 * @Param stack l'ItemStack
	 * @Return la variante correspondant à stack
	 */
	@Override
	 public String getUnlocalizedName(ItemStack stack) {
		
        int i = stack.getMetadata();
        return super.getUnlocalizedName() + "." + EnumVariant.byMetadata(i).getName();
    }
	
	/*
	 * Permet de récupérer les items des variantes
	 * @Param tab (CreativeTabs)
	 * @Param subItems les variantes
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(final CreativeTabs tab, final NonNullList<ItemStack> subItems) {
		
		final List<ItemStack> items = Stream.of(EnumVariant.values())
				.map(enumType -> new ItemStack(this, 1, enumType.getMeta()))
				.collect(Collectors.toList());

		subItems.addAll(items);
	}
	
	@Override
	public EnumVariant[] getValues() {
		
		return EnumVariant.values();
	}
}
