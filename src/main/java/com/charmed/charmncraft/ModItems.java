package com.charmed.charmncraft;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    // Define the compass as a simple item
    public static final Item EXPLORERS_COMPASS = registerItem("explorers_compass",
            new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(CharmNCraft.MOD_ID, name), item);
    }

    public static void initialize() {
        CharmNCraft.LOGGER.info("Registering items.");
    }
}