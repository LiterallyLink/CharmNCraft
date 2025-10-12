package com.charmed.charmncraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharmNCraft implements ModInitializer {
    public static final String MOD_ID = "charmncraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[CharmNCraft] Initialize");

        ModBlocks.initialize();
        PressurePlateGenerator.initialize();
        VillagerNameManager.initialize();

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.FROSTED_STONE_BRICKS);
            entries.add(ModBlocks.FROSTED_STONE_BRICK_STAIRS);
            entries.add(ModBlocks.FROSTED_STONE_BRICK_SLAB);
            entries.add(ModBlocks.FROSTED_STONE_BRICK_WALL);

            PressurePlateGenerator.getGeneratedPlates().forEach(plate -> {
                entries.add(plate.block());
            });
        });
    }
}