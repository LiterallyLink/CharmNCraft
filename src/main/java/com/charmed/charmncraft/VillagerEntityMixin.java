package com.charmed.charmncraft.mixin;

import com.charmed.charmncraft.VillagerNameManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin extends MerchantEntity {
    @Unique
    private static final String NAME_ASSIGNED_TAG = "charmncraft:name_assigned";

    public VillagerEntityMixin(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void assignNameOnFirstTick(CallbackInfo ci) {
        // Only run on server side
        if (this.getWorld().isClient()) {
            return;
        }

        // Check if we've already assigned a name
        if (this.getCommandTags().contains(NAME_ASSIGNED_TAG)) {
            return;
        }

        // Check if this villager already has a custom name
        if (this.hasCustomName()) {
            // Mark as assigned so we don't override player-given names
            this.addCommandTag(NAME_ASSIGNED_TAG);
            return;
        }

        // Assign a random name based on gender (50/50 chance)
        String name;
        if (this.getWorld().getRandom().nextBoolean()) {
            name = VillagerNameManager.getRandomMaleName();
        } else {
            name = VillagerNameManager.getRandomFemaleName();
        }

        this.setCustomName(Text.literal(name));
        this.setCustomNameVisible(false); // Only show when looking at them
        this.addCommandTag(NAME_ASSIGNED_TAG);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void ensureNameTagPersists(NbtCompound nbt, CallbackInfo ci) {
        // When loading from NBT, if we have a custom name, mark as assigned
        if (this.hasCustomName() && !this.getCommandTags().contains(NAME_ASSIGNED_TAG)) {
            this.addCommandTag(NAME_ASSIGNED_TAG);
        }
    }
}