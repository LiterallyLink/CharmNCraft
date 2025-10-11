package com.charmed.charmncraft.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ExplorersCompassScreen extends Screen {

    public ExplorersCompassScreen() {
        super(Text.literal("Explorer's Compass"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);

        context.drawCenteredTextWithShadow(
                this.textRenderer,
                "Press Esc to close",
                this.width / 2,
                this.height / 2,
                0xFFFFFF
        );

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}