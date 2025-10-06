package com.charmed.charmncraft.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(AnimalEntity.class)
public class AnimalEntityMixin {
    private static final Random RANDOM = new Random();
    private static final double KILLER_RABBIT_CHANCE = 0.05; // 5% chance

    @Inject(
            method = "breed",
            at = @At("HEAD")
    )
    private void charmncraft_onBreedAttempt(ServerWorld world, AnimalEntity other, CallbackInfo ci) {
        // Check if both parents are rabbits
        AnimalEntity thisAnimal = (AnimalEntity)(Object)this;
        if (thisAnimal.getType() == EntityType.RABBIT && other.getType() == EntityType.RABBIT) {
            // You could add special logic here if needed
            // For example, checking if parents have certain NBT tags or names
        }
    }
}