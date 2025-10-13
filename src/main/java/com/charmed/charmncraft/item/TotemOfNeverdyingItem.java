package com.charmed.charmncraft.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TotemOfNeverdyingItem extends Item {
    private static final String LIVES_KEY = "Lives";
    private static final int MAX_LIVES = 3;

    public TotemOfNeverdyingItem(Settings settings) {
        super(settings);
    }

    public static int getLives(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.contains(LIVES_KEY)) {
            nbt.putInt(LIVES_KEY, MAX_LIVES);
        }
        return nbt.getInt(LIVES_KEY);
    }

    public static void setLives(ItemStack stack, int lives) {
        stack.getOrCreateNbt().putInt(LIVES_KEY, Math.max(0, Math.min(lives, MAX_LIVES)));
    }

    public static boolean useLife(ItemStack stack, LivingEntity entity) {
        int lives = getLives(stack);
        if (lives <= 0) {
            return false;
        }

        setLives(stack, lives - 1);

        // Apply totem effects
        entity.setHealth(1.0F);
        entity.clearStatusEffects();
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));

        entity.getWorld().sendEntityStatus(entity, (byte)35);

        if (entity instanceof ServerPlayerEntity player) {
            player.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        // Remove totem if no lives left
        if (getLives(stack) <= 0) {
            stack.decrement(1);
        }

        return true;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return getLives(stack) == MAX_LIVES;
    }
}