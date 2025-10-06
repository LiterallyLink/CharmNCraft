package com.charmed.charmncraft.mixin;

import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RabbitEntity.class)
public abstract class RabbitGoalsMixin {

    @Shadow
    protected abstract boolean isEvil();

    @Shadow
    protected GoalSelector goalSelector;

    @Shadow
    protected GoalSelector targetSelector;

    @Inject(
            method = "initGoals",
            at = @At("TAIL")
    )
    private void charmncraft_modifyKillerRabbitGoals(CallbackInfo ci) {
        RabbitEntity rabbit = (RabbitEntity)(Object)this;

        // Check if this is our passive killer rabbit
        if (isEvil() && rabbit.getCommandTags().contains("charmncraft:passive_killer")) {
            // Remove all hostile goals (they're added in vanilla for killer rabbits)
            this.targetSelector.clear(goal -> goal instanceof ActiveTargetGoal);
            this.goalSelector.clear(goal -> goal instanceof MeleeAttackGoal);

            // Add the tempt goal for golden carrots
            this.goalSelector.add(3, new TemptGoal(rabbit, 1.0D, Ingredient.ofItems(Items.GOLDEN_CARROT), false));

            // Add a follow goal for players holding golden carrots
            this.goalSelector.add(4, new FollowMobGoal(rabbit, 1.0D, 10.0F, 2.0F) {
                @Override
                public boolean canStart() {
                    if (super.canStart()) {
                        return this.target instanceof PlayerEntity player &&
                                player.getMainHandStack().isOf(Items.GOLDEN_CARROT);
                    }
                    return false;
                }
            });
        }
    }
}