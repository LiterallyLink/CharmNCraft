package com.charmed.charmncraft;

import com.charmed.charmncraft.item.TotemOfNeverdyingItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item NETHERITE_NUGGET = registerItem("netherite_nugget",
            new Item(new FabricItemSettings().fireproof()));

    public static final Item NETHERITE_APPLE = registerItem("netherite_apple",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder()
                            .hunger(4)
                            .saturationModifier(1.2f)
                            .alwaysEdible()
                            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 2), 1.0f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 1), 1.0f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0), 1.0f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 1), 1.0f)
                            .build())
                    .fireproof()
                    .rarity(Rarity.RARE)));

    public static final Item ENCHANTED_NETHERITE_APPLE = registerItem("enchanted_netherite_apple",
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder()
                            .hunger(4)
                            .saturationModifier(1.2f)
                            .alwaysEdible()
                            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600, 4), 1.0f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 12000, 4), 1.0f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 12000, 0), 1.0f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 12000, 3), 1.0f)
                            .build())
                    .fireproof()
                    .rarity(Rarity.EPIC)) {
                @Override
                public boolean hasGlint(net.minecraft.item.ItemStack stack) {
                    return true;
                }
            });

    public static final Item TOTEM_OF_NEVERDYING = registerItem("totem_of_neverdying",
            new TotemOfNeverdyingItem(new FabricItemSettings()
                    .fireproof()
                    .maxCount(1)
                    .rarity(Rarity.UNCOMMON)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(CharmNCraft.MOD_ID, name), item);
    }

    public static void initialize() {
        CharmNCraft.LOGGER.info("Registering items.");
    }
}