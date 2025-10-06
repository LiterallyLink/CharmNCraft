package com.charmed.charmncraft;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block FROSTED_STONE_BRICKS = registerBlock("frosted_stone_bricks",
            new Block(FabricBlockSettings.create()
                    .strength(1.5f, 6.0f)
                    .sounds(BlockSoundGroup.STONE)
                    .requiresTool()));

    public static final Block FROSTED_STONE_BRICK_STAIRS = registerBlock("frosted_stone_brick_stairs",
            new StairsBlock(FROSTED_STONE_BRICKS.getDefaultState(),
                    FabricBlockSettings.copyOf(FROSTED_STONE_BRICKS)));

    public static final Block FROSTED_STONE_BRICK_SLAB = registerBlock("frosted_stone_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(FROSTED_STONE_BRICKS)));

    public static final Block FROSTED_STONE_BRICK_WALL = registerBlock("frosted_stone_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(FROSTED_STONE_BRICKS)));

    private static Block registerBlock(String name, Block block) {
        Registry.register(Registries.BLOCK, new Identifier(CharmNCraft.MOD_ID, name), block);
        Registry.register(Registries.ITEM, new Identifier(CharmNCraft.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        return block;
    }

    public static void initialize() {
        CharmNCraft.LOGGER.info("Registering blocks.");
    }
}
