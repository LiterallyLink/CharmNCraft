package com.charmed.charmncraft.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ECStructureSearchGUI extends Screen {

    public ECStructureSearchGUI() {
        super(Text.literal("Explorer's Compass"));
    }

    @Override
    protected void init() {
        super.init();
        // We'll add buttons here
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Draw background
        this.renderBackground(context);

        // Draw title
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false; // Don't pause the game when this screen is open
    }
}