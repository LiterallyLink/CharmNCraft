package com.charmed.charmncraft.mixin;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.RabbitEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RabbitEntity.class)
public class RabbitEntityMixin {

    @Inject(
            method = "setVariant",
            at = @At("HEAD"),
            cancellable = true
    )
    private void preventHostileGoalsForPassiveKiller(RabbitEntity.RabbitType rabbitType, CallbackInfo ci) {
        RabbitEntity rabbit = (RabbitEntity)(Object)this;

        if (rabbit.getWorld().isClient) {
            return;
        }

        if (rabbit.getCommandTags().contains("charmncraft:passive_killer") &&
                rabbitType == RabbitEntity.RabbitType.EVIL) {

            TrackedData<Integer> rabbitTypeData = RabbitEntityAccessor.getRabbitTypeTrackedData();
            rabbit.getDataTracker().set(rabbitTypeData, rabbitType.getId());

            ci.cancel();
        }
    }
}