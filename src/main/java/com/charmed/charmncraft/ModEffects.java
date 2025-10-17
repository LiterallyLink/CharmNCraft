package com.charmed.charmncraft;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final StatusEffect ENDER_PHASING = registerEffect("ender_phasing",
            new StatusEffect(StatusEffectCategory.NEUTRAL, 0x8B008B) {}); // Dark magenta color

    private static StatusEffect registerEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(CharmNCraft.MOD_ID, name), effect);
    }

    public static void initialize() {
        CharmNCraft.LOGGER.info("Registering effects.");
    }
}