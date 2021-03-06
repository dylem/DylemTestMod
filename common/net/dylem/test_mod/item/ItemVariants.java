package net.dylem.test_mod.item;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.dylem.test_mod.util.IVariant;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * item avec plusieurs variantes
 */
public class ItemVariants extends ItemTestMod implements IVariant.IItemVariant {
	
	public ItemVariants(final String itemName) {
		
		super(itemName);
	}
	
	/*
	 * Retourne l'unlocalized name correspondant � la variante
	 * @Param stack l'ItemStack
	 * @Return la variante correspondant � stack
	 * @see net.minecraft.item.Item#getUnlocalizedName(net.minecraft.item.ItemStack)
	 */
	@Override
	 public String getUnlocalizedName(ItemStack stack) {
		
        int i = stack.getMetadata();
        return super.getUnlocalizedName() + "." + EnumItemVariants.byMetadata(i).getName();
    }
	
	/*
	 * Permet de r�cup�rer les items des variantes
	 * @Param tab (CreativeTabs)
	 * @Param subItems les variantes
	 * @see net.minecraft.item.Item#getSubItems(net.minecraft.creativetab.CreativeTabs, net.minecraft.util.NonNullList)
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(final CreativeTabs tab, final NonNullList<ItemStack> subItems) {
		
		final List<ItemStack> items = Stream.of(EnumItemVariants.values())
				.map(enumType -> new ItemStack(this, 1, enumType.getMeta())) // Pour chaque variante, on cr�e un itemstack en fonction de sa meta
				.collect(Collectors.toList()); // Et on les mets dans une liste

		subItems.addAll(items);
	}
	
	/*
	 * @see net.dylem.test_mod.util.IVariant.IItemVariant#getValues()
	 */
	@Override
	public EnumItemVariants[] getValues() {
		
		return EnumItemVariants.values();
	}
}
