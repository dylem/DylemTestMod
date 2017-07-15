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

	// Méthode d'aide pour créer facilement les instances lambda de cette classe
	static ItemMeshDefinition create(final MeshDefinitionFix lambda) {
		return lambda;
	}

	@Override
	default ModelResourceLocation getModelLocation(final ItemStack stack) {
		return getLocation(stack);
	}
}