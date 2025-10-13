package com.charmed.charmncraft.mixin;

import com.charmed.charmncraft.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class PiglinBruteLootMixin {

    @Inject(
            method = "dropLoot",
            at = @At("TAIL")
    )
    private void addNetheriteNuggetDrop(DamageSource damageSource, boolean causedByPlayer, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;

        // Only run on server side
        if (entity.getWorld().isClient()) {
            return;
        }

        // Check if the entity is a Piglin Brute
        if (entity.getType() == EntityType.PIGLIN_BRUTE) {
            // 50% chance to drop Netherite Nugget
            if (entity.getWorld().getRandom().nextFloat() < 0.5f) {
                ServerWorld world = (ServerWorld) entity.getWorld();
                ItemStack nugget = new ItemStack(ModItems.NETHERITE_NUGGET);
                entity.dropStack(nugget);
            }
        }
    }
}