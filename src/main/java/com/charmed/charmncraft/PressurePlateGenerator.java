package com.charmed.charmncraft;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PressurePlateGenerator {
    private static final Gson GSON = new Gson();
    private static final List<GeneratedPressurePlate> GENERATED_PLATES = new ArrayList<>();

    public static void initialize() {
        loadConfigAndGenerate();
    }

    private static void loadConfigAndGenerate() {
        try {
            InputStream stream = PressurePlateGenerator.class
                    .getResourceAsStream("/pressure_plate_config.json");

            if (stream == null) {
                CharmNCraft.LOGGER.warn("No pressure plate config found");
                return;
            }

            JsonObject config = GSON.fromJson(new InputStreamReader(stream), JsonObject.class);
            JsonArray pressurePlates = config.getAsJsonArray("pressure_plates");

            for (int i = 0; i < pressurePlates.size(); i++) {
                JsonObject entry = pressurePlates.get(i).getAsJsonObject();
                String baseBlock = entry.get("base_block").getAsString();
                String material = entry.get("material").getAsString();
                String activationRule = entry.get("activation_rule").getAsString();

                // Default to 0.5f if not specified
                float strength = entry.has("strength") ? entry.get("strength").getAsFloat() : 0.5f;

                generatePressurePlate(baseBlock, material, activationRule, strength);
            }

            CharmNCraft.LOGGER.info("Generated {} pressure plates", GENERATED_PLATES.size());

        } catch (Exception e) {
            CharmNCraft.LOGGER.error("Failed to load pressure plate config", e);
        }
    }

    private static void generatePressurePlate(String baseBlockId, String material, String activationRule, float strength) {
        Identifier baseId = new Identifier(baseBlockId);
        Block baseBlock = Registries.BLOCK.get(baseId);

        if (baseBlock == null) {
            CharmNCraft.LOGGER.warn("Base block not found: {}", baseBlockId);
            return;
        }

        String plateName = baseId.getPath() + "_pressure_plate";
        Identifier plateId = new Identifier(CharmNCraft.MOD_ID, plateName);

        BlockSetType blockSetType = switch (material.toLowerCase()) {
            case "wood" -> BlockSetType.OAK;
            case "stone" -> BlockSetType.STONE;
            case "metal" -> BlockSetType.IRON;
            default -> BlockSetType.STONE;
        };

        PressurePlateBlock.ActivationRule rule = switch (activationRule.toLowerCase()) {
            case "everything" -> PressurePlateBlock.ActivationRule.EVERYTHING;
            case "mobs" -> PressurePlateBlock.ActivationRule.MOBS;
            default -> PressurePlateBlock.ActivationRule.MOBS;
        };

        PressurePlateBlock pressurePlate = new PressurePlateBlock(
                rule,
                FabricBlockSettings.copyOf(baseBlock)
                        .noCollision()
                        .strength(strength),
                blockSetType
        );

        Registry.register(Registries.BLOCK, plateId, pressurePlate);
        Registry.register(Registries.ITEM, plateId,
                new BlockItem(pressurePlate, new FabricItemSettings()));

        GENERATED_PLATES.add(new GeneratedPressurePlate(
                plateId, baseId, pressurePlate, material
        ));
    }

    public static List<GeneratedPressurePlate> getGeneratedPlates() {
        return GENERATED_PLATES;
    }

    public record GeneratedPressurePlate(
            Identifier plateId,
            Identifier baseBlockId,
            PressurePlateBlock block,
            String material
    ) {}
}