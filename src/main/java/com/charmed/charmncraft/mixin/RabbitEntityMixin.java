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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(AnimalEntity.class)
public abstract class RabbitEntityMixin extends PassiveEntity {
    private static final Random RANDOM = new Random();
    private static final double KILLER_RABBIT_CHANCE = 0.05;

    protected RabbitEntityMixin(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "breed(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/AnimalEntity;Lnet/minecraft/entity/passive/PassiveEntity;)V",
            at = @At("HEAD")
    )
    private void charmncraft_createPassiveKillerRabbit(ServerWorld world, AnimalEntity other, PassiveEntity baby, CallbackInfo ci) {
        // Check if both parents are rabbits
        AnimalEntity thisAnimal = (AnimalEntity)(Object)this;
        if (thisAnimal.getType() != EntityType.RABBIT || other.getType() != EntityType.RABBIT) {
            return;
        }

        // Check if baby is a rabbit and roll for killer variant
        if (baby instanceof RabbitEntity rabbitBaby && RANDOM.nextDouble() < KILLER_RABBIT_CHANCE) {
            // Set to killer rabbit type (99)
            TrackedData<Integer> rabbitTypeData = RabbitEntityAccessor.getRabbitTypeTrackedData();
            rabbitBaby.getDataTracker().set(rabbitTypeData, 99);

            // Mark as passive killer
            rabbitBaby.addCommandTag("charmncraft:passive_killer");
        }
    }
}