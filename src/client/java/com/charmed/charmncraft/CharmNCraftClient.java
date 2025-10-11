package com.charmed.charmncraft;

import com.charmed.charmncraft.screen.ExplorersCompassScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.util.Identifier;

public class CharmNCraftClient implements ClientModInitializer {
    public static final Identifier OPEN_COMPASS_SCREEN = new Identifier(CharmNCraft.MOD_ID, "open_compass_screen");

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(OPEN_COMPASS_SCREEN, (client, handler, buf, responseSender) -> {
            client.execute(() -> {
                client.setScreen(new ExplorersCompassScreen());
            });
        });
    }
}