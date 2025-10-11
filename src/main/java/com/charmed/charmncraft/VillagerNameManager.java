package com.charmed.charmncraft;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VillagerNameManager {
    private static final List<String> MALE_NAMES = new ArrayList<>();
    private static final List<String> FEMALE_NAMES = new ArrayList<>();
    private static final Random RANDOM = new Random();
    private static final Gson GSON = new Gson();

    public static void initialize() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return new Identifier(CharmNCraft.MOD_ID, "villager_names");
            }

            @Override
            public void reload(ResourceManager manager) {
                loadNames(manager, "male_names", MALE_NAMES);
                loadNames(manager, "female_names", FEMALE_NAMES);

                CharmNCraft.LOGGER.info("Loaded {} male names and {} female names",
                        MALE_NAMES.size(), FEMALE_NAMES.size());
            }
        });
    }

    private static void loadNames(ResourceManager manager, String filename, List<String> targetList) {
        targetList.clear();

        Identifier id = new Identifier(CharmNCraft.MOD_ID, "villager_names/" + filename + ".json");

        try {
            manager.getAllResources(id).forEach(resource -> {
                try (InputStream stream = resource.getInputStream()) {
                    JsonObject json = GSON.fromJson(new InputStreamReader(stream), JsonObject.class);
                    json.getAsJsonArray("names").forEach(element -> {
                        targetList.add(element.getAsString());
                    });
                } catch (Exception e) {
                    CharmNCraft.LOGGER.error("Failed to load names from {}", id, e);
                }
            });
        } catch (Exception e) {
            CharmNCraft.LOGGER.error("Failed to find resource {}", id, e);
        }

        if (targetList.isEmpty()) {
            CharmNCraft.LOGGER.warn("No names loaded for {}, using fallback", filename);
            if (filename.equals("male_names")) {
                targetList.add("Steve");
            } else {
                targetList.add("Alex");
            }
        }
    }

    public static String getRandomMaleName() {
        if (MALE_NAMES.isEmpty()) return "Steve";
        return MALE_NAMES.get(RANDOM.nextInt(MALE_NAMES.size()));
    }

    public static String getRandomFemaleName() {
        if (FEMALE_NAMES.isEmpty()) return "Alex";
        return FEMALE_NAMES.get(RANDOM.nextInt(FEMALE_NAMES.size()));
    }
}