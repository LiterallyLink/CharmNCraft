package com.charmed.charmncraft.client;

import com.charmed.charmncraft.gui.ECStructureSearchGUI;
import net.minecraft.client.MinecraftClient;

public class ClientHelper {

    public static void openExplorerCompassGui() {
        MinecraftClient.getInstance().setScreen(new ECStructureSearchGUI());
    }
}