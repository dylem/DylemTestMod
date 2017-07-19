package net.dylem.test_mod.event;

import java.util.Optional;

import net.dylem.test_mod.item.EnumSoulSword;
import net.dylem.test_mod.item.ItemSoulSword;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * Classe contenant tous les �v�nements du mod involvant une entit�.
 * Classe automatiquement abonn�e � MinecraftForge.EVENT_BUS
 */
@Mod.EventBusSubscriber
public class ModEntityEvent {
	
	/*
	 * Si une entit� a �t� tu�e par un certain item.
	 * 
	 * Ev�nement �x�cut� par :
	 * {@link EntityLivingBase#onDeath(DamageSource)},
	 * {@link EntityPlayer#onDeath(DamageSource)},
	 * {@link EntityPlayerMP#onDeath(DamageSource)}.
	 * 
	 * @Param event L'�v�nement
	 */
	@SubscribeEvent
	public static void entityKilledByItem(LivingDeathEvent event) {
		
		// Si l'�v�nement a �t� caus� par une entit�
		if(event.getSource() instanceof EntityDamageSource) {
			
			// Forc�ment une EntityLivingBase (voir @link)
			EntityLivingBase entity = (EntityLivingBase) ((EntityDamageSource)event.getSource()).getTrueSource();
			
			ItemStack stack = entity.getHeldItemMainhand();
			
			// @see net.dylem.test_mod.item.ItemBloodSword
			if(stack != null && stack.getItem() instanceof ItemSoulSword) {
				
				entity.setHeldItem(EnumHand.MAIN_HAND, ((ItemSoulSword)stack.getItem()).addSoul(stack));
			}	
		}
	}
}
