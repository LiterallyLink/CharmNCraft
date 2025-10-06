package com.charmed.charmncraft.mixin;

import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RabbitEntity.class)
public abstract class RabbitGoalsMixin {

    @Inject(
            method = "initGoals",
            at = @At("TAIL")
    )
    private void charmncraft_modifyKillerRabbitGoals(CallbackInfo ci) {
        RabbitEntity rabbit = (RabbitEntity)(Object)this;

        // Check if this is our passive killer rabbit
        // We check the variant type directly since we set it to EVIL
        if (rabbit.getRabbitType() == 99 && rabbit.getCommandTags().contains("charmncraft:passive_killer")) {
            // Cast to access the goal selectors
            MobEntityAccessor accessor = (MobEntityAccessor) rabbit;
            GoalSelector goalSelector = accessor.getGoalSelector();
            GoalSelector targetSelector = accessor.getTargetSelector();

            // Remove hostile targeting goals that vanilla adds for killer rabbits
            targetSelector.getGoals().removeIf(prioritizedGoal ->
                    prioritizedGoal.getGoal() instanceof ActiveTargetGoal);

            // Remove melee attack goal
            goalSelector.getGoals().removeIf(prioritizedGoal ->
                    prioritizedGoal.getGoal() instanceof MeleeAttackGoal);

            // Add the tempt goal for golden carrots at high priority
            // This will make them follow players holding golden carrots
            goalSelector.add(2, new TemptGoal(rabbit, 1.2D, Ingredient.ofItems(Items.GOLDEN_CARROT), false));
        }
    }
}