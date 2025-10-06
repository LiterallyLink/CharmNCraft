package com.charmed.charmncraft.mixin;

import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

@Mixin(AnimalEntity.class)
public class AnimalEntityMixin {
    private static final Random RANDOM = new Random();
    private static final double KILLER_RABBIT_CHANCE = 0.05; // 5% chance

    @Inject(
            method = "breed(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/AnimalEntity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/world/ServerWorld;spawnEntityAndPassengers(Lnet/minecraft/entity/Entity;)V"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void charmncraft_onRabbitBred(ServerWorld world, AnimalEntity other, CallbackInfo ci, PassiveEntity baby) {
        if (baby instanceof RabbitEntity babyRabbit) {
            AnimalEntity thisAnimal = (AnimalEntity)(Object)this;
            if (thisAnimal instanceof RabbitEntity) {
                if (RANDOM.nextDouble() < KILLER_RABBIT_CHANCE) {
                    // Use NBT to set the rabbit type to killer rabbit (99)
                    NbtCompound nbt = new NbtCompound();
                    babyRabbit.writeNbt(nbt);
                    nbt.putInt("RabbitType", 99);
                    babyRabbit.readNbt(nbt);

                    // Add tempt goal (follows when holding golden carrot) - that's it!
                    MobEntityAccessor accessor = (MobEntityAccessor) babyRabbit;
                    accessor.getGoalSelector().add(3, new TemptGoal(babyRabbit, 1.0D, Ingredient.ofItems(Items.GOLDEN_CARROT), false));

                    // NO attack goals - passive killer rabbit!
                }
            }
        }
    }
}