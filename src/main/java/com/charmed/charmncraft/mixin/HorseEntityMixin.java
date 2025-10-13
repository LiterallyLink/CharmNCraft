package com.charmed.charmncraft.mixin;

import com.charmed.charmncraft.ModItems;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(AbstractHorseEntity.class)
public abstract class HorseEntityMixin {

    @Shadow
    protected SimpleInventory items;

    @ModifyVariable(
            method = "damage",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 0
    )
    private float reduceNetheriteLavaDamage(float amount, DamageSource source) {
        // Get armor from inventory slot 1 (armor slot for horses)
        ItemStack armor = this.items != null ? this.items.getStack(1) : ItemStack.EMPTY;

        if (armor.isOf(ModItems.NETHERITE_HORSE_ARMOR)) {
            // Check if it's fire or lava damage
            if (source.isIn(net.minecraft.registry.tag.DamageTypeTags.IS_FIRE)) {
                // Reduce fire/lava damage by 80%
                return amount * 0.2f;
            }
        }

        return amount;
    }
}