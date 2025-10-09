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

@Mixin(AnimalEntity.class)
public class AnimalEntityMixin {
    private static final double KILLER_RABBIT_CHANCE = 0.05; // 5%

    @Inject(
            method = "breed(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/AnimalEntity;Lnet/minecraft/entity/passive/PassiveEntity;)V",
            at = @At("HEAD")
    )
    private void spawnPassiveKillerRabbit(ServerWorld world, AnimalEntity other, PassiveEntity baby, CallbackInfo ci) {
        AnimalEntity thisAnimal = (AnimalEntity)(Object)this;

        if (thisAnimal.getType() == EntityType.RABBIT && other.getType() == EntityType.RABBIT) {
            if (baby instanceof RabbitEntity rabbitBaby) {
                if (world.getRandom().nextDouble() < KILLER_RABBIT_CHANCE) {
                    // Add tag first, before setting variant
                    rabbitBaby.addCommandTag("charmncraft:passive_killer");

                    // Set variant to killer rabbit
                    rabbitBaby.setVariant(RabbitEntity.RabbitType.EVIL);

                    // Remove the "The Killer Rabbit" name
                    rabbitBaby.setCustomName(null);
                    rabbitBaby.setCustomNameVisible(false);
                }
            }
        }
    }
}