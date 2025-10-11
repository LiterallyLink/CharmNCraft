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
        if (this.getWorld().isClient()) {
            return;
        }

        if (this.getCommandTags().contains(NAME_ASSIGNED_TAG)) {
            return;
        }

        if (this.hasCustomName()) {
            this.addCommandTag(NAME_ASSIGNED_TAG);
            return;
        }

        String name;
        if (this.getWorld().getRandom().nextBoolean()) {
            name = VillagerNameManager.getRandomMaleName();
        } else {
            name = VillagerNameManager.getRandomFemaleName();
        }

        this.setCustomName(Text.literal(name));
        this.setCustomNameVisible(false);
        this.addCommandTag(NAME_ASSIGNED_TAG);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void ensureNameTagPersists(NbtCompound nbt, CallbackInfo ci) {
        if (this.hasCustomName() && !this.getCommandTags().contains(NAME_ASSIGNED_TAG)) {
            this.addCommandTag(NAME_ASSIGNED_TAG);
        }
    }
}