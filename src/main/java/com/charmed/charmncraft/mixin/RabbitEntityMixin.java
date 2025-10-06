package com.charmed.charmncraft.mixin;

import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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

    @Shadow
    public abstract void setVariant(RabbitEntity.RabbitType variant);

    @Shadow
    protected abstract void initGoals();

    @Inject(
            method = "createChild(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/AnimalEntity;)Lnet/minecraft/entity/passive/RabbitEntity;",
            at = @At("RETURN")
    )
    private void charmncraft_modifyBabyRabbit(ServerWorld world, AnimalEntity other, CallbackInfoReturnable<RabbitEntity> cir) {
        RabbitEntity baby = cir.getReturnValue();

        if (baby != null && RANDOM.nextDouble() < KILLER_RABBIT_CHANCE) {
            // Set the rabbit to be a killer rabbit variant
            baby.setVariant(RabbitEntity.RabbitType.EVIL);

            // Add custom NBT tag to mark this as a "tamed" killer rabbit
            baby.addCommandTag("charmncraft:passive_killer");

            // The rabbit will already have the evil appearance but we need to modify its behavior
            // We'll handle the behavior changes in a separate mixin that targets goal initialization
        }
    }
}