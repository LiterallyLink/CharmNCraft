package com.charmed.charmncraft;

import com.charmed.charmncraft.blocks.ChorusPieBlock;
import com.charmed.charmncraft.blocks.EndstoneStoveBlock;
import com.charmed.charmncraft.blocks.GoldenCakeBlock;
import com.charmed.charmncraft.blocks.StuffedShulkerBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.block.piston.PistonBehavior;

/**
 * Central registry for all custom blocks in CharmNCraft.
 * All blocks are registered to the "charmncraft" namespace.
 * 
 * Each block is automatically registered as a BlockItem for inventory/creative tabs.
 * 
 * Texture Path: assets/charmncraft/textures/block/
 * Model Path: assets/charmncraft/models/block/
 * Blockstate Path: assets/charmncraft/blockstates/
 */
public class ModBlocks {
    // ==================== GOLDEN FOODS BLOCKS ====================
    public static final Block GOLDEN_CAKE = registerBlock("golden_cake",
            new GoldenCakeBlock(AbstractBlock.Settings.copy(Blocks.CAKE)));

    // ==================== ENDER'S DELIGHT BLOCKS ====================
    public static final Block ENDSTONE_STOVE = registerBlock("endstone_stove",
            new EndstoneStoveBlock(AbstractBlock.Settings.copy(Blocks.STONE)
                    .luminance(state -> state.get(EndstoneStoveBlock.LIT) ? 13 : 0)));
    public static final Block CHORUS_CRATE = registerBlock("chorus_crate",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block CHORUS_PIE = registerBlock("chorus_pie",
            new ChorusPieBlock(AbstractBlock.Settings.copy(Blocks.CAKE)));
    public static final Block STUFFED_SHULKER_BLOCK = registerBlock("stuffed_shulker_block",
            new StuffedShulkerBlock(AbstractBlock.Settings.copy(Blocks.SHULKER_BOX).pistonBehavior(PistonBehavior.DESTROY)));

    // ==================== CASUALNESS DELIGHT BLOCKS ====================
    public static final Block BOBO_CHICKEN = registerBlock("bobo_chicken",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block CHEESE_WHEEL = registerBlock("cheese_wheel",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block RAW_CHEESE_WHEEL = registerBlock("raw_cheese_wheel",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block PAPER_WRAPPED_FISH = registerBlock("paper_wrapped_fish",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block PLATE_OF_FRIED_DUMPLING = registerBlock("plate_of_fried_dumpling",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block QUICHE_LORRAINE = registerBlock("quiche_lorraine",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block SPRING_ROLL_MEDLEY = registerBlock("spring_roll_medley",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block STARGAZY_PIE = registerBlock("stargazy_pie",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block SWEET_RICE = registerBlock("sweet_rice",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block DEEP_FRYING_PAN = registerBlock("deep_frying_pan",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));

    /**
     * Registers a block and its corresponding BlockItem.
     * This ensures the block appears in inventories and creative tabs.
     * 
     * Best Practice: Always call this instead of directly registering blocks
     * to ensure consistent BlockItem registration.
     * 
     * @param name The block's identifier name (lowercase with underscores)
     * @param block The Block instance to register
     * @return The registered Block
     */
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(CharmNCraft.MOD_ID, name), block);
    }

    /**
     * Registers the BlockItem for a given block.
     * Called automatically by registerBlock().
     * 
     * @param name The block's identifier name
     * @param block The Block to create an item for
     * @return The registered BlockItem
     */
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(CharmNCraft.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    /**
     * Called during mod initialization to log block registration status.
     */
    public static void initialize() {
        CharmNCraft.LOGGER.info("Registering blocks.");
    }
}
