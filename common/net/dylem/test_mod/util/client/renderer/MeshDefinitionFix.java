package net.dylem.test_mod.util.client.renderer;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

/**
 * Permet l'utilisation des lambdas sans causer d'erreur à ForgeGradle
 * @author diesieben07
 */
public interface MeshDefinitionFix extends ItemMeshDefinition {
	ModelResourceLocation getLocation(final ItemStack stack);
	
	/*
	 * Méthode d'aide pour créer facilement les instances lambda de cette classe
	 * @Param lambda Le lambda de ce type
	 * @Return l'instance de type ItemMeshDefinition correspondant
	 */
	static ItemMeshDefinition create(final MeshDefinitionFix lambda) {
		return lambda;
	}
	
	/*
	 * @see net.minecraft.client.renderer.ItemMeshDefinition#getModelLocation(net.minecraft.item.ItemStack)
	 */
	@Override
	default ModelResourceLocation getModelLocation(final ItemStack stack) {
		return getLocation(stack);
	}
}