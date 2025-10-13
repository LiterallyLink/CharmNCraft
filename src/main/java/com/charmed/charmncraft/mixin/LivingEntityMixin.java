package com.charmed.charmncraft.mixin;

import com.charmed.charmncraft.ModItems;
import com.charmed.charmncraft.item.TotemOfNeverdyingItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow
    protected abstract ItemStack getStackInHand(Hand hand);

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void tryUseNeverdyingTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity)(Object)this;

        if (entity.getWorld().isClient) {
            return;
        }

        ItemStack mainHandStack = entity.getStackInHand(Hand.MAIN_HAND);
        ItemStack offHandStack = entity.getStackInHand(Hand.OFF_HAND);

        ItemStack totemStack = null;
        if (mainHandStack.isOf(ModItems.TOTEM_OF_NEVERDYING)) {
            totemStack = mainHandStack;
            if (TotemOfNeverdyingItem.useLife(totemStack, entity, (TotemOfNeverdyingItem) ModItems.TOTEM_OF_NEVERDYING)) {
                cir.setReturnValue(true);
            }
        } else if (offHandStack.isOf(ModItems.TOTEM_OF_NEVERDYING)) {
            totemStack = offHandStack;
            if (TotemOfNeverdyingItem.useLife(totemStack, entity, (TotemOfNeverdyingItem) ModItems.TOTEM_OF_NEVERDYING)) {
                cir.setReturnValue(true);
            }
        }
    }
}