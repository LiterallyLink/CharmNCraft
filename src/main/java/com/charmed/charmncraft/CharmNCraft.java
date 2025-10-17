package com.charmed.charmncraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main mod entry point for CharmNCraft.
 * Initializes all blocks, items, and effect groups.
 * 
 * Namespace: charmncraft (MOD_ID)
 * All assets stored under assets/charmncraft/
 */
public class CharmNCraft implements ModInitializer {
    public static final String MOD_ID = "charmncraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Golden Foods Item Group
    public static final ItemGroup GOLDEN_FOODS_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.GOLDEN_COOKIE))
            .displayName(Text.literal("Golden Foods"))
            .entries((context, entries) -> {
                entries.add(ModItems.GOLDEN_BAKED_POTATO);
                entries.add(ModItems.GOLDEN_BEETROOT);
                entries.add(ModItems.GOLDEN_BEETROOT_SOUP);
                entries.add(ModItems.GOLDEN_BREAD);
                entries.add(ModItems.GOLDEN_CHORUS_FRUIT);
                entries.add(ModItems.GOLDEN_COOKED_BEEF);
                entries.add(ModItems.GOLDEN_COOKED_CHICKEN);
                entries.add(ModItems.GOLDEN_COOKED_COD);
                entries.add(ModItems.GOLDEN_COOKED_MUTTON);
                entries.add(ModItems.GOLDEN_COOKED_PORKCHOP);
                entries.add(ModItems.GOLDEN_COOKED_RABBIT);
                entries.add(ModItems.GOLDEN_COOKED_SALMON);
                entries.add(ModItems.GOLDEN_COOKIE);
                entries.add(ModItems.GOLDEN_DRIED_KELP);
                entries.add(ModItems.GOLDEN_GLOW_BERRIES);
                entries.add(ModItems.GOLDEN_HONEY_BOTTLE);
                entries.add(ModItems.GOLDEN_MELON_SLICE);
                entries.add(ModItems.GOLDEN_MILK_BUCKET);
                entries.add(ModItems.GOLDEN_MUSHROOM_STEW);
                entries.add(ModItems.GOLDEN_PUMPKIN_PIE);
                entries.add(ModItems.GOLDEN_RABBIT_STEW);
                entries.add(ModItems.GOLDEN_SWEET_BERRIES);
                
                // Golden Food Blocks
                entries.add(ModBlocks.GOLDEN_CAKE);
            })
            .build();

    // Ender's Delight Item Group
    public static final ItemGroup ENDERS_DELIGHT_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.ENDER_PAELLA))
            .displayName(Text.literal("Ender's Delight"))
            .entries((context, entries) -> {
                entries.add(ModItems.ENDER_SHARD);
                entries.add(ModItems.ENDERMAN_SIGHT);
                entries.add(ModItems.SIGHT_FRAGMENT);
                entries.add(ModItems.MITE_CRUST);
                entries.add(ModItems.SHULKER_BOWL);
                entries.add(ModItems.SHULKER_MOLLUSK);
                entries.add(ModItems.SHULKER_FILET);
                entries.add(ModItems.CHORUS_JUICE);
                entries.add(ModItems.CHORUS_PIE_SLICE);
                entries.add(ModItems.CHORUS_STEW);
                entries.add(ModItems.CRAWLING_SANDWICH);
                entries.add(ModItems.CRISPY_SKEWER);
                entries.add(ModItems.ENDER_PAELLA);
                entries.add(ModItems.ENDERMITE_STEW);
                entries.add(ModItems.PEARL_PASTA);
                entries.add(ModItems.STUFFED_SHULKER_BOWL);
                entries.add(ModItems.TWISTED_CEREAL);
                entries.add(ModItems.UNCANNY_COOKIES);
                entries.add(ModItems.STRANGE_ECLAIR);

                // Blocks
                entries.add(ModBlocks.ENDSTONE_STOVE);
                entries.add(ModBlocks.CHORUS_CRATE);
                entries.add(ModBlocks.CHORUS_PIE);
                entries.add(ModBlocks.STUFFED_SHULKER_BLOCK);
            })
            .build();

    // Casualness Delight Item Group
    public static final ItemGroup CASUALNESS_DELIGHT_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.BOBO_CHICKEN))
            .displayName(Text.literal("Casualness Delight"))
            .entries((context, entries) -> {
                // Cooked dishes
                entries.add(ModItems.BEEF_NOODLES);
                entries.add(ModBlocks.BOBO_CHICKEN);
                entries.add(ModItems.FISH_AND_CHIPS);
                entries.add(ModBlocks.PAPER_WRAPPED_FISH);
                entries.add(ModItems.PHANTOM_DUMPLINGS);
                entries.add(ModItems.PHANTOM_PUFF);
                entries.add(ModBlocks.SWEET_RICE);
                entries.add(ModItems.SPICY_STRIPS);
                entries.add(ModItems.GREEN_TONGUE);
                entries.add(ModItems.YORKSHIRE_PUDDING);
                entries.add(ModItems.DONKEY_BURGER);

                // Fried items
                entries.add(ModItems.FRIED_DUMPLING);
                entries.add(ModItems.FRIED_FISH);
                entries.add(ModItems.FRIED_CHICKEN_CHIP);
                entries.add(ModItems.POTATO_CHIP);
                entries.add(ModItems.SPRING_ROLL);
                entries.add(ModBlocks.SPRING_ROLL_MEDLEY);
                entries.add(ModItems.TONKATSU);

                // Bowled items
                entries.add(ModItems.BOWL_OF_FRIED_DUMPLING);
                entries.add(ModItems.BOWL_OF_PAPER_WRAPPED_FISH);
                entries.add(ModItems.BOWL_OF_SWEET_RICE);

                // Cheese products
                entries.add(ModBlocks.CHEESE_WHEEL);
                entries.add(ModItems.CHEESE_WHEEL_SLICE);

                // Pie products
                entries.add(ModBlocks.QUICHE_LORRAINE);
                entries.add(ModItems.QUICHE_LORRAINE_SLICE);
                entries.add(ModBlocks.STARGAZY_PIE);
                entries.add(ModItems.STARGAZY_PIE_SLICE);

                // Gluten products
                entries.add(ModItems.GLUTEN);
                entries.add(ModItems.ROAST_GLUTEN);
                entries.add(ModItems.GLUTEN_SKEWER);

                // Raw items
                entries.add(ModItems.RAW_FRIED_DUMPLING);
                entries.add(ModItems.RAW_SPRING_ROLL);
                entries.add(ModItems.RAW_GLUTEN);
                entries.add(ModItems.RAW_CABBAGE_BOBO_CHICKEN);
                entries.add(ModItems.CABBAGE_BOBO_CHICKEN);
                entries.add(ModItems.RAW_CHICKEN_BOBO_CHICKEN);
                entries.add(ModItems.CHICKEN_BOBO_CHICKEN);
                entries.add(ModItems.RAW_POTATO_BOBO_CHICKEN);
                entries.add(ModItems.POTATO_BOBO_CHICKEN);
                entries.add(ModItems.POTATO_SLICE);

                // Meat products
                entries.add(ModItems.COOKED_DONKEY_MEAT);
                entries.add(ModItems.RAW_DONKEY_MEAT);

                // Special blocks
                entries.add(ModBlocks.PLATE_OF_FRIED_DUMPLING);
                entries.add(ModBlocks.RAW_CHEESE_WHEEL);
                entries.add(ModBlocks.DEEP_FRYING_PAN);
            })
            .build();

    @Override
    public void onInitialize() {
        LOGGER.info("[CharmNCraft] Initialize");
        ModBlocks.initialize();
        ModItems.initialize();
        ModEffects.initialize();

        // Register the custom item groups (3 categories only)
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "golden_foods"), GOLDEN_FOODS_GROUP);
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "enders_delight"), ENDERS_DELIGHT_GROUP);
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "casualness_delight"), CASUALNESS_DELIGHT_GROUP);

        // Add items to creative tabs
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(ModItems.GOLDEN_BAKED_POTATO);
            entries.add(ModItems.GOLDEN_BEETROOT);
            entries.add(ModItems.GOLDEN_BEETROOT_SOUP);
            entries.add(ModItems.GOLDEN_BREAD);
            entries.add(ModItems.GOLDEN_CHORUS_FRUIT);
            entries.add(ModItems.GOLDEN_COOKED_BEEF);
            entries.add(ModItems.GOLDEN_COOKED_CHICKEN);
            entries.add(ModItems.GOLDEN_COOKED_COD);
            entries.add(ModItems.GOLDEN_COOKED_MUTTON);
            entries.add(ModItems.GOLDEN_COOKED_PORKCHOP);
            entries.add(ModItems.GOLDEN_COOKED_RABBIT);
            entries.add(ModItems.GOLDEN_COOKED_SALMON);
            entries.add(ModItems.GOLDEN_COOKIE);
            entries.add(ModItems.GOLDEN_DRIED_KELP);
            entries.add(ModItems.GOLDEN_GLOW_BERRIES);
            entries.add(ModItems.GOLDEN_HONEY_BOTTLE);
            entries.add(ModItems.GOLDEN_MELON_SLICE);
            entries.add(ModItems.GOLDEN_MILK_BUCKET);
            entries.add(ModItems.GOLDEN_MUSHROOM_STEW);
            entries.add(ModItems.GOLDEN_PUMPKIN_PIE);
            entries.add(ModItems.GOLDEN_RABBIT_STEW);
            entries.add(ModItems.GOLDEN_SWEET_BERRIES);

            // Ender's Delight items
            entries.add(ModItems.ENDER_SHARD);
            entries.add(ModItems.ENDERMAN_SIGHT);
            entries.add(ModItems.SIGHT_FRAGMENT);
            entries.add(ModItems.MITE_CRUST);
            entries.add(ModItems.SHULKER_BOWL);
            entries.add(ModItems.SHULKER_MOLLUSK);
            entries.add(ModItems.SHULKER_FILET);
            entries.add(ModItems.CHORUS_JUICE);
            entries.add(ModItems.CHORUS_PIE_SLICE);
            entries.add(ModItems.CHORUS_STEW);
            entries.add(ModItems.CRAWLING_SANDWICH);
            entries.add(ModItems.CRISPY_SKEWER);
            entries.add(ModItems.ENDER_PAELLA);
            entries.add(ModItems.ENDERMITE_STEW);
            entries.add(ModItems.PEARL_PASTA);
            entries.add(ModItems.STUFFED_SHULKER_BOWL);
            entries.add(ModItems.TWISTED_CEREAL);
            entries.add(ModItems.UNCANNY_COOKIES);
            entries.add(ModItems.STRANGE_ECLAIR);

            // Casualness Delight items
            entries.add(ModItems.BEEF_NOODLES);
            entries.add(ModBlocks.BOBO_CHICKEN);
            entries.add(ModItems.FISH_AND_CHIPS);
            entries.add(ModBlocks.PAPER_WRAPPED_FISH);
            entries.add(ModItems.PHANTOM_DUMPLINGS);
            entries.add(ModItems.PHANTOM_PUFF);
            entries.add(ModBlocks.SWEET_RICE);
            entries.add(ModItems.SPICY_STRIPS);
            entries.add(ModItems.GREEN_TONGUE);
            entries.add(ModItems.YORKSHIRE_PUDDING);
            entries.add(ModItems.DONKEY_BURGER);
            entries.add(ModItems.FRIED_DUMPLING);
            entries.add(ModItems.FRIED_FISH);
            entries.add(ModItems.FRIED_CHICKEN_CHIP);
            entries.add(ModItems.POTATO_CHIP);
            entries.add(ModItems.SPRING_ROLL);
            entries.add(ModBlocks.SPRING_ROLL_MEDLEY);
            entries.add(ModItems.TONKATSU);
            entries.add(ModItems.BOWL_OF_FRIED_DUMPLING);
            entries.add(ModItems.BOWL_OF_PAPER_WRAPPED_FISH);
            entries.add(ModItems.BOWL_OF_SWEET_RICE);
            entries.add(ModBlocks.CHEESE_WHEEL);
            entries.add(ModItems.CHEESE_WHEEL_SLICE);
            entries.add(ModBlocks.QUICHE_LORRAINE);
            entries.add(ModItems.QUICHE_LORRAINE_SLICE);
            entries.add(ModBlocks.STARGAZY_PIE);
            entries.add(ModItems.STARGAZY_PIE_SLICE);
            entries.add(ModItems.CABBAGE_BOBO_CHICKEN);
            entries.add(ModItems.CHICKEN_BOBO_CHICKEN);
            entries.add(ModItems.POTATO_BOBO_CHICKEN);
            entries.add(ModItems.ROAST_GLUTEN);
            entries.add(ModItems.GLUTEN_SKEWER);
            entries.add(ModItems.COOKED_DONKEY_MEAT);
            entries.add(ModBlocks.PLATE_OF_FRIED_DUMPLING);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.GOLDEN_CAKE);
            entries.add(ModBlocks.ENDSTONE_STOVE);
            entries.add(ModBlocks.CHORUS_CRATE);
            entries.add(ModBlocks.CHORUS_PIE);
            entries.add(ModBlocks.STUFFED_SHULKER_BLOCK);
            entries.add(ModBlocks.BOBO_CHICKEN);
            entries.add(ModBlocks.CHEESE_WHEEL);
            entries.add(ModBlocks.RAW_CHEESE_WHEEL);
            entries.add(ModBlocks.PAPER_WRAPPED_FISH);
            entries.add(ModBlocks.PLATE_OF_FRIED_DUMPLING);
            entries.add(ModBlocks.QUICHE_LORRAINE);
            entries.add(ModBlocks.SPRING_ROLL_MEDLEY);
            entries.add(ModBlocks.STARGAZY_PIE);
            entries.add(ModBlocks.SWEET_RICE);
            entries.add(ModBlocks.DEEP_FRYING_PAN);
        });

        LOGGER.info("[CharmNCraft] Mod initialization complete. Items registered: {}", ModItems.getRegisteredItemCount());
    }
}
