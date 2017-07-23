	package net.dylem.test_mod.item;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.Multimap;

import net.dylem.test_mod.TestMod;
import net.dylem.test_mod.util.IVariant;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Une épée qui garde les ames des énemis en mémoire,
 * dont la puissance (attaque/vitesse) augmente de 0.1 pour chaque ennemi tué,
 * et qui double de puissance à certains paliers
 */
public class ItemSoulSword extends ItemSword implements IVariant.IItemVariant {
	
	// Etapes de changement de metadata
	private final int META_STEPS[] = {10, 25, 50};
	
	public ItemSoulSword(Item.ToolMaterial material, final String itemName) {
		
		super(material);
		
		ItemTestMod.setItemName(this, itemName);
		setCreativeTab(TestMod.TEST_TAB);
		
		// Les damages de l'Item sont déjà utilisés par la metadata
		this.setMaxDamage(-1);
	}
	
	/*
	 * Initialise le nombre d'ames de ce stack
	 * @Param stack L'itemstack
	 */
	private void initiateSouls(ItemStack stack) {
		
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger("souls", 0); // nombre d'ames
		stack.getTagCompound().setDouble("increase", 0); // augmentation
	}
	
	/*
	 * Ajouter 1 au nombre d'ames et ajoute 0.1 de puissance
	 * Si le nombre d'ames atteint un palier, change la métadata et multiplie par 2 la puissance ajoutée
	 * 
	 * @Param stack L'itemstack
	 * @Return stack L'itemstack
	 */
	public ItemStack addSoul(ItemStack stack) {
		
		final int i = getSouls(stack) + 1;
		final double d = getIncrease(stack) + 0.1;
		
		// Envoi du nouveau nombre d'ames
		stack.getTagCompound().setInteger("souls", i);
		
		if(IntStream.of(META_STEPS).anyMatch(step -> i == step)) { // Si on atteint une étape, 
			
			stack.getTagCompound().setDouble("increase", d * 2); // double la puissance
			stack.setItemDamage(stack.getItemDamage() + 1); // augmente la metadata
		}
		else {
			stack.getTagCompound().setDouble("increase", d); // sinon on envoie l'augmentation normale
		}
		
		return stack;
	}
	
	/*
	 * @Param stack L'itemStack
	 * @Return le nombre d'ames "capturées"
	 */
	private int getSouls(ItemStack stack) {
		
		if (!stack.hasTagCompound()) {
			initiateSouls(stack);
        }
		
		return stack.getTagCompound().getInteger("souls");
	}

	/*
	 * @Param stack L'itemStack
	 * @Return l'augmentation actuelle
	 */
	private double getIncrease(ItemStack stack) {
		
		return stack.getTagCompound().getDouble("increase");
	}
	
	/*
	 * Permet de récupérer les attributs modifiés de l'arme
	 * 
	 * @see net.minecraft.item.Item#getAttributeModifiers(net.minecraft.inventory.EntityEquipmentSlot, net.minecraft.item.ItemStack)
	 * @Param slot Le slot actuellement utilisé
	 * @Param stack L'itemstack dans le slot
	 * @return modifier La map modifiée
	 */
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(final EntityEquipmentSlot slot, final ItemStack stack) {
		
		// Si on a tué au moins une entité, et que l'item est en utilisation, alors on augmente la puissance de l'arme
		if (getSouls(stack) > 0 && slot == EntityEquipmentSlot.MAINHAND) {
			
			Multimap<String, AttributeModifier> modifiers = super.getAttributeModifiers(EntityEquipmentSlot.MAINHAND, stack);
			alterAttributeModifier(modifiers, SharedMonsterAttributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER, getIncrease(stack));
			alterAttributeModifier(modifiers, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, getIncrease(stack));
			
			return modifiers;
		}
		
		// Sinon on utilise la fonction d'ItemSword
		return getItemAttributeModifiers(slot);
	}
	
	/*
	 * Altère un modifieur d'attribut
	 * 
	 * @Param multimap La multimap
	 * @Param modifierName le nom de l'attribut modifié
	 * @Param id l'id du modifieur 
	 * @Param d le multiplicateur
	 */
	private void alterAttributeModifier(final Multimap<String, AttributeModifier> multimap, 
					final IAttribute attribute, final UUID id, final double d) {
		
		final Collection<AttributeModifier> modifiers = multimap.get(attribute.getName());
		final Optional<AttributeModifier> optional = modifiers.stream().filter(attributeModifier -> attributeModifier.getID().equals(id)).findFirst();
		
		optional.ifPresent(modifier -> {
			
			AttributeModifier newModifier = new AttributeModifier(modifier.getID(), modifier.getName(), modifier.getAmount() + d, modifier.getOperation());
			
			modifiers.remove(modifier);
			modifiers.add(newModifier);
		});
	}
	
	/*
	 * Retourne l'unlocalized name correspondant à la variante
	 * @Param stack l'ItemStack
	 * @Return la variante correspondant à stack
	 * @see net.minecraft.item.Item#getUnlocalizedName(net.minecraft.item.ItemStack)
	 */
	@Override
	 public String getUnlocalizedName(ItemStack stack) {
		
       int i = stack.getMetadata();
       return super.getUnlocalizedName() + "." + EnumSoulSword.byMetadata(i).getName();
   }
	
	/*
	 * Permet de récupérer les items des variantes
	 * @Param tab (CreativeTabs)
	 * @Param subItems les variantes
	 * @see net.minecraft.item.Item#getSubItems(net.minecraft.creativetab.CreativeTabs, net.minecraft.util.NonNullList)
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(final CreativeTabs tab, final NonNullList<ItemStack> subItems) {
		
		final List<ItemStack> items = Stream.of(EnumSoulSword.values())
				.map(enumType -> new ItemStack(this, 1, enumType.getMeta()))
				.collect(Collectors.toList());

		subItems.addAll(items);
	}
	
	
	/*
	 * @see net.dylem.test_mod.util.IVariant.IItemVariant#getValues()
	 */
	@Override
	public EnumSoulSword[] getValues() {
		
		return EnumSoulSword.values();
	}
}
