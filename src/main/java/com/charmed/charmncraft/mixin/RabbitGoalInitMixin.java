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
public abstract class RabbitGoalInitMixin {

    @Inject(
            method = "initGoals",
            at = @At("TAIL")
    )
    private void charmncraft_modifyPassiveKillerRabbitGoals(CallbackInfo ci) {
        RabbitEntity rabbit = (RabbitEntity)(Object)this;

        // Check if this is our passive killer rabbit (tag set during breeding)
        if (rabbit.getCommandTags().contains("charmncraft:passive_killer")) {
            MobEntityAccessor accessor = (MobEntityAccessor) rabbit;
            GoalSelector goalSelector = accessor.getGoalSelector();
            GoalSelector targetSelector = accessor.getTargetSelector();

            // Remove hostile goals that vanilla adds to killer rabbits
            targetSelector.getGoals().removeIf(prioritizedGoal ->
                    prioritizedGoal.getGoal() instanceof ActiveTargetGoal);
            goalSelector.getGoals().removeIf(prioritizedGoal ->
                    prioritizedGoal.getGoal() instanceof MeleeAttackGoal);

            // Add tempt goal for golden carrots
            goalSelector.add(2, new TemptGoal(rabbit, 1.2D, Ingredient.ofItems(Items.GOLDEN_CARROT), false));
        }
    }
}