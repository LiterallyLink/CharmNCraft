package com.charmed.charmncraft.mixin;

import com.charmed.charmncraft.ModItems;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorMaterials.class)
public class NetheriteArmorMaterialMixin {
    @Inject(method = "getRepairIngredient", at = @At("RETURN"), cancellable = true)
    private void modifyNetheriteRepairIngredient(CallbackInfoReturnable<Ingredient> cir) {
        if ((Object) this == ArmorMaterials.NETHERITE) {
            cir.setReturnValue(Ingredient.ofItems(
                    net.minecraft.item.Items.NETHERITE_INGOT,
                    ModItems.NETHERITE_NUGGET
            ));
        }
    }
}