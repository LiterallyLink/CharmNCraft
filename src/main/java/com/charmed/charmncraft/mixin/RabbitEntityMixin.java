package com.charmed.charmncraft.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(RabbitEntity.class)
public abstract class RabbitEntityMixin extends AnimalEntity {
    private static final Random RANDOM = new Random();
    private static final double KILLER_RABBIT_CHANCE = 0.05; // 5% chance

    // This is required for the super constructor
    protected RabbitEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "createChild(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/PassiveEntity;)Lnet/minecraft/entity/passive/PassiveEntity;",
            at = @At("RETURN")
    )
    private void charmncraft_modifyBabyRabbit(ServerWorld world, PassiveEntity other, CallbackInfoReturnable<PassiveEntity> cir) {
        PassiveEntity baby = cir.getReturnValue();

        if (baby instanceof RabbitEntity rabbitBaby && RANDOM.nextDouble() < KILLER_RABBIT_CHANCE) {
            // Set the rabbit to be a killer rabbit variant (type 99) using the data tracker directly
            TrackedData<Integer> rabbitTypeData = RabbitEntityAccessor.getRabbitTypeTrackedData();
            rabbitBaby.getDataTracker().set(rabbitTypeData, 99);

            // Add custom NBT tag to mark this as a "tamed" killer rabbit
            rabbitBaby.addCommandTag("charmncraft:passive_killer");

            // The rabbit will already have the evil appearance but we need to modify its behavior
            // We'll handle the behavior changes in a separate mixin that targets goal initialization
        }
    }
}