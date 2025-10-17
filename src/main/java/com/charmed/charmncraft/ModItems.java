package com.charmed.charmncraft;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Central registry for all custom items in CharmNCraft.
 * All items are registered to the "charmncraft" namespace.
 * 
 * Organization:
 * - Golden Foods Items: Premium vanilla food variants
 * - Ender's Delight Items: End-themed foods and components
 * - Casualness Delight Items: Casual/comfort foods
 * 
 * Texture Path: assets/charmncraft/textures/item/
 * Model Path: assets/charmncraft/models/item/
 */
public class ModItems {
    // ==================== GOLDEN FOODS ITEMS ====================
    public static final Item GOLDEN_BAKED_POTATO = registerItem("golden_baked_potato", new Item(new Item.Settings().food(ModFoods.GOLDEN_BAKED_POTATO)));
    public static final Item GOLDEN_BEETROOT = registerItem("golden_beetroot", new Item(new Item.Settings().food(ModFoods.GOLDEN_BEETROOT)));
    public static final Item GOLDEN_BEETROOT_SOUP = registerItem("golden_beetroot_soup", new Item(new Item.Settings().food(ModFoods.GOLDEN_BEETROOT_SOUP)));
    public static final Item GOLDEN_BREAD = registerItem("golden_bread", new Item(new Item.Settings().food(ModFoods.GOLDEN_BREAD)));
    public static final Item GOLDEN_CHORUS_FRUIT = registerItem("golden_chorus_fruit", new Item(new Item.Settings().food(ModFoods.GOLDEN_CHORUS_FRUIT)));
    public static final Item GOLDEN_COOKED_BEEF = registerItem("golden_cooked_beef", new Item(new Item.Settings().food(ModFoods.GOLDEN_COOKED_BEEF)));
    public static final Item GOLDEN_COOKED_CHICKEN = registerItem("golden_cooked_chicken", new Item(new Item.Settings().food(ModFoods.GOLDEN_COOKED_CHICKEN)));
    public static final Item GOLDEN_COOKED_COD = registerItem("golden_cooked_cod", new Item(new Item.Settings().food(ModFoods.GOLDEN_COOKED_COD)));
    public static final Item GOLDEN_COOKED_MUTTON = registerItem("golden_cooked_mutton", new Item(new Item.Settings().food(ModFoods.GOLDEN_COOKED_MUTTON)));
    public static final Item GOLDEN_COOKED_PORKCHOP = registerItem("golden_cooked_porkchop", new Item(new Item.Settings().food(ModFoods.GOLDEN_COOKED_PORKCHOP)));
    public static final Item GOLDEN_COOKED_RABBIT = registerItem("golden_cooked_rabbit", new Item(new Item.Settings().food(ModFoods.GOLDEN_COOKED_RABBIT)));
    public static final Item GOLDEN_COOKED_SALMON = registerItem("golden_cooked_salmon", new Item(new Item.Settings().food(ModFoods.GOLDEN_COOKED_SALMON)));
    public static final Item GOLDEN_COOKIE = registerItem("golden_cookie", new Item(new Item.Settings().food(ModFoods.GOLDEN_COOKIE)));
    public static final Item GOLDEN_DRIED_KELP = registerItem("golden_dried_kelp", new Item(new Item.Settings().food(ModFoods.GOLDEN_DRIED_KELP)));
    public static final Item GOLDEN_GLOW_BERRIES = registerItem("golden_glow_berries", new Item(new Item.Settings().food(ModFoods.GOLDEN_GLOW_BERRIES)));
    public static final Item GOLDEN_HONEY_BOTTLE = registerItem("golden_honey_bottle", new Item(new Item.Settings().food(ModFoods.GOLDEN_HONEY_BOTTLE)));
    public static final Item GOLDEN_MELON_SLICE = registerItem("golden_melon_slice", new Item(new Item.Settings().food(ModFoods.GOLDEN_MELON_SLICE)));
    public static final Item GOLDEN_MILK_BUCKET = registerItem("golden_milk_bucket", new Item(new Item.Settings().food(ModFoods.GOLDEN_MILK_BUCKET)));
    public static final Item GOLDEN_MUSHROOM_STEW = registerItem("golden_mushroom_stew", new Item(new Item.Settings().food(ModFoods.GOLDEN_MUSHROOM_STEW)));
    public static final Item GOLDEN_PUMPKIN_PIE = registerItem("golden_pumpkin_pie", new Item(new Item.Settings().food(ModFoods.GOLDEN_PUMPKIN_PIE)));
    public static final Item GOLDEN_RABBIT_STEW = registerItem("golden_rabbit_stew", new Item(new Item.Settings().food(ModFoods.GOLDEN_RABBIT_STEW)));
    public static final Item GOLDEN_SWEET_BERRIES = registerItem("golden_sweet_berries", new Item(new Item.Settings().food(ModFoods.GOLDEN_SWEET_BERRIES)));

    // ==================== ENDER'S DELIGHT ITEMS ====================
    // Drops and Components
    public static final Item ENDER_SHARD = registerItem("ender_shard", new Item(new Item.Settings()));
    public static final Item ENDERMAN_SIGHT = registerItem("enderman_sight", new Item(new Item.Settings()));
    public static final Item SIGHT_FRAGMENT = registerItem("sight_fragment", new Item(new Item.Settings()));
    public static final Item MITE_CRUST = registerItem("mite_crust", new Item(new Item.Settings()));
    public static final Item SHULKER_BOWL = registerItem("shulker_bowl", new Item(new Item.Settings()));
    public static final Item SHULKER_MOLLUSK = registerItem("shulker_mollusk", new Item(new Item.Settings()));
    public static final Item SHULKER_FILET = registerItem("shulker_filet", new Item(new Item.Settings()));

    // Ender's Delight Foods
    public static final Item CHORUS_JUICE = registerItem("chorus_juice", new Item(new Item.Settings().food(ModFoods.CHORUS_JUICE)));
    public static final Item CHORUS_PIE_SLICE = registerItem("chorus_pie_slice", new Item(new Item.Settings().food(ModFoods.CHORUS_PIE_SLICE)));
    public static final Item CHORUS_STEW = registerItem("chorus_stew", new Item(new Item.Settings().food(ModFoods.CHORUS_STEW)));
    public static final Item CRAWLING_SANDWICH = registerItem("crawling_sandwich", new Item(new Item.Settings().food(ModFoods.CRAWLING_SANDWICH)));
    public static final Item CRISPY_SKEWER = registerItem("crispy_skewer", new Item(new Item.Settings().food(ModFoods.CRISPY_SKEWER)));
    public static final Item ENDER_PAELLA = registerItem("ender_paella", new Item(new Item.Settings().food(ModFoods.ENDER_PAELLA)));
    public static final Item ENDERMITE_STEW = registerItem("endermite_stew", new Item(new Item.Settings().food(ModFoods.ENDERMITE_STEW)));
    public static final Item PEARL_PASTA = registerItem("pearl_pasta", new Item(new Item.Settings().food(ModFoods.PEARL_PASTA)));
    public static final Item STUFFED_SHULKER_BOWL = registerItem("stuffed_shulker_bowl", new Item(new Item.Settings().food(ModFoods.STUFFED_SHULKER_BOWL)));
    public static final Item TWISTED_CEREAL = registerItem("twisted_cereal", new Item(new Item.Settings().food(ModFoods.TWISTED_CEREAL)));
    public static final Item UNCANNY_COOKIES = registerItem("uncanny_cookies", new Item(new Item.Settings().food(ModFoods.UNCANNY_COOKIES)));
    public static final Item STRANGE_ECLAIR = registerItem("strange_eclair", new Item(new Item.Settings().food(ModFoods.STRANGE_ECLAIR)));

    // ==================== CASUALNESS DELIGHT ITEMS ====================
    // Cooked Dishes
    public static final Item BEEF_NOODLES = registerItem("beef_noodles", new Item(new Item.Settings().food(ModFoods.BEEF_NOODLES)));
    public static final Item FISH_AND_CHIPS = registerItem("fish_and_chips", new Item(new Item.Settings().food(ModFoods.FISH_AND_CHIPS)));
    public static final Item PHANTOM_DUMPLINGS = registerItem("phantom_dumplings", new Item(new Item.Settings().food(ModFoods.PHANTOM_DUMPLINGS)));
    public static final Item PHANTOM_PUFF = registerItem("phantom_puff", new Item(new Item.Settings().food(ModFoods.PHANTOM_PUFF)));
    public static final Item SPICY_STRIPS = registerItem("spicy_strips", new Item(new Item.Settings().food(ModFoods.SPICY_STRIPS)));
    public static final Item GREEN_TONGUE = registerItem("green_tongue", new Item(new Item.Settings().food(ModFoods.GREEN_TONGUE)));
    public static final Item YORKSHIRE_PUDDING = registerItem("yorkshire_pudding", new Item(new Item.Settings().food(ModFoods.YORKSHIRE_PUDDING)));
    public static final Item DONKEY_BURGER = registerItem("donkey_burger", new Item(new Item.Settings().food(ModFoods.DONKEY_BURGER)));

    // Fried Items
    public static final Item FRIED_DUMPLING = registerItem("fried_dumpling", new Item(new Item.Settings().food(ModFoods.FRIED_DUMPLING)));
    public static final Item FRIED_FISH = registerItem("fried_fish", new Item(new Item.Settings().food(ModFoods.FRIED_FISH)));
    public static final Item FRIED_CHICKEN_CHIP = registerItem("fried_chicken_chip", new Item(new Item.Settings().food(ModFoods.FRIED_CHICKEN_CHIP)));
    public static final Item POTATO_CHIP = registerItem("potato_chip", new Item(new Item.Settings().food(ModFoods.POTATO_CHIP)));
    public static final Item SPRING_ROLL = registerItem("spring_roll", new Item(new Item.Settings().food(ModFoods.SPRING_ROLL)));
    public static final Item TONKATSU = registerItem("tonkatsu", new Item(new Item.Settings().food(ModFoods.TONKATSU)));

    // Bowled Items
    public static final Item BOWL_OF_FRIED_DUMPLING = registerItem("bowl_of_fried_dumpling", new Item(new Item.Settings().food(ModFoods.BOWL_OF_FRIED_DUMPLING)));
    public static final Item BOWL_OF_PAPER_WRAPPED_FISH = registerItem("bowl_of_paper_wrapped_fish", new Item(new Item.Settings().food(ModFoods.BOWL_OF_PAPER_WRAPPED_FISH)));
    public static final Item BOWL_OF_SWEET_RICE = registerItem("bowl_of_sweet_rice", new Item(new Item.Settings().food(ModFoods.BOWL_OF_SWEET_RICE)));

    // Cheese Products
    public static final Item CHEESE_WHEEL_SLICE = registerItem("cheese_wheel_slice", new Item(new Item.Settings().food(ModFoods.CHEESE_WHEEL_SLICE)));

    // Pie Slices
    public static final Item QUICHE_LORRAINE_SLICE = registerItem("quiche_lorraine_slice", new Item(new Item.Settings().food(ModFoods.QUICHE_LORRAINE_SLICE)));
    public static final Item STARGAZY_PIE_SLICE = registerItem("stargazy_pie_slice", new Item(new Item.Settings().food(ModFoods.STARGAZY_PIE_SLICE)));

    // Gluten Products
    public static final Item GLUTEN = registerItem("gluten", new Item(new Item.Settings().food(ModFoods.GLUTEN)));
    public static final Item ROAST_GLUTEN = registerItem("roast_gluten", new Item(new Item.Settings().food(ModFoods.ROAST_GLUTEN)));
    public static final Item GLUTEN_SKEWER = registerItem("gluten_skewer", new Item(new Item.Settings().food(ModFoods.GLUTEN_SKEWER)));

    // Raw Items
    public static final Item RAW_FRIED_DUMPLING = registerItem("raw_fried_dumpling", new Item(new Item.Settings().food(ModFoods.RAW_FRIED_DUMPLING)));
    public static final Item RAW_SPRING_ROLL = registerItem("raw_spring_roll", new Item(new Item.Settings().food(ModFoods.RAW_SPRING_ROLL)));
    public static final Item RAW_GLUTEN = registerItem("raw_gluten", new Item(new Item.Settings().food(ModFoods.RAW_GLUTEN)));
    public static final Item RAW_CABBAGE_BOBO_CHICKEN = registerItem("raw_cabbage_bobo_chicken", new Item(new Item.Settings().food(ModFoods.RAW_CABBAGE_BOBO_CHICKEN)));
    public static final Item CABBAGE_BOBO_CHICKEN = registerItem("cabbage_bobo_chicken", new Item(new Item.Settings().food(ModFoods.CABBAGE_BOBO_CHICKEN)));
    public static final Item RAW_CHICKEN_BOBO_CHICKEN = registerItem("raw_chicken_bobo_chicken", new Item(new Item.Settings().food(ModFoods.RAW_CHICKEN_BOBO_CHICKEN)));
    public static final Item CHICKEN_BOBO_CHICKEN = registerItem("chicken_bobo_chicken", new Item(new Item.Settings().food(ModFoods.CHICKEN_BOBO_CHICKEN)));
    public static final Item RAW_POTATO_BOBO_CHICKEN = registerItem("raw_potato_bobo_chicken", new Item(new Item.Settings().food(ModFoods.RAW_POTATO_BOBO_CHICKEN)));
    public static final Item POTATO_BOBO_CHICKEN = registerItem("potato_bobo_chicken", new Item(new Item.Settings().food(ModFoods.POTATO_BOBO_CHICKEN)));
    public static final Item POTATO_SLICE = registerItem("potato_slice", new Item(new Item.Settings().food(ModFoods.POTATO_SLICE)));

    // Meat Products
    public static final Item COOKED_DONKEY_MEAT = registerItem("cooked_donkey_meat", new Item(new Item.Settings().food(ModFoods.COOKED_DONKEY_MEAT)));
    public static final Item RAW_DONKEY_MEAT = registerItem("raw_donkey_meat", new Item(new Item.Settings().food(ModFoods.RAW_DONKEY_MEAT)));

    /**
     * Registers an item with the charmncraft namespace.
     * 
     * @param name The item's identifier name (lowercase with underscores)
     * @param item The Item instance to register
     * @return The registered Item
     */
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(CharmNCraft.MOD_ID, name), item);
    }

    /**
     * Called during mod initialization to log item registration status.
     */
    public static void initialize() {
        CharmNCraft.LOGGER.info("Registering items.");
        CharmNCraft.LOGGER.info("Total items registered: {}", getRegisteredItemCount());
    }

    /**
     * Counts the total number of static final Item fields in this class.
     * Used for debugging and initialization logging.
     * 
     * @return The count of registered items
     */
    public static int getRegisteredItemCount() {
        int count = 0;
        for (java.lang.reflect.Field field : ModItems.class.getDeclaredFields()) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) &&
                java.lang.reflect.Modifier.isFinal(field.getModifiers()) &&
                field.getType().equals(net.minecraft.item.Item.class)) {
                count++;
            }
        }
        return count;
    }
}
