package net.dylem.test_mod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/*
 * Un totem qui permet au joueur de se t�l�porter � une position enregistr�e au pr�alable
 */
public class ItemTeleportationTotem extends ItemTestMod {

	public ItemTeleportationTotem(String itemName) {
		
		super(itemName);
	}
	
	/* Permet d'effectuer une action en appuyant sur le clic droit avec l'item en main
	 * 
	 * @see net.minecraft.item.Item#onItemRightClick(net.minecraft.world.World, net.minecraft.entity.player.EntityPlayer, net.minecraft.util.EnumHand)
	 * @Param world Le monde
	 * @Param player Le joueur
	 * @Param hand La main dans laquelle l'item est tenu
	 * @Return Le r�sultat de l'action, si tout s'est bien d�roul�
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		
		final ItemStack stack = player.getHeldItem(hand);
		
		if(player.isSneaking()) { // Si le joueur est en train d'appuyer sur shift
			
			if(!stack.hasTagCompound()) { // Initialisation du NBTTag
				stack.setTagCompound(new NBTTagCompound());
			}
			
			// On enregistre la position actuelle
			BlockPos pos = player.getPosition();
			int[] posArray = { pos.getX(), pos.getY(), pos.getZ() };
			stack.getTagCompound().setIntArray("pos", posArray);
			
		} else {
			
			BlockPos pos;
			
			if(stack.hasTagCompound()) { // Si on a enregistr� une position
				// R�cup�ration de la position
				int[] posArray = stack.getTagCompound().getIntArray("pos");
				pos = new BlockPos(posArray[0], posArray[1], posArray[2]);
			} else { // sinon, on se t�l�porte au spawn
				pos = world.getSpawnPoint();
			}
			
			while(!world.isAirBlock(pos)) { // Au cas o� le point de t�l�portation ne contiendrait pas de l'air
				pos = pos.up(); // Alors on le met plus haut
				System.out.println(pos.getY());
			}
			player.setPosition(pos.getX(), pos.getY(), pos.getZ()); // T�l�portation
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
}
