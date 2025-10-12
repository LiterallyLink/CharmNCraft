package com.charmed.charmncraft.screen;

import com.charmed.charmncraft.CharmNCraft;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class ExplorersCompassScreen extends Screen {
    private static final int BUTTON_PADDING_X = 20;
    private static final int BUTTON_PADDING_Y = 6;
    private static final int MARGIN = 10;
    private static final int SPACING = 5;

    private TextFieldWidget searchField;
    private ButtonWidget teleportButton;
    private ButtonWidget selectStructureButton;
    private ButtonWidget startSearchButton;
    private ButtonWidget sortByButton;

    public ExplorersCompassScreen() {
        super(Text.literal("Explorer's Compass"));
    }

    @Override
    protected void init() {
        super.init();

        int buttonHeight = this.textRenderer.fontHeight + (BUTTON_PADDING_Y * 2);
        int cancelWidth = this.textRenderer.getWidth("Cancel") + (BUTTON_PADDING_X * 2);
        int teleportWidth = this.textRenderer.getWidth("Teleport") + (BUTTON_PADDING_X * 2);
        int selectWidth = this.textRenderer.getWidth("Select Structure") + (BUTTON_PADDING_X * 2);
        int searchWidth = this.textRenderer.getWidth("Start Search") + (BUTTON_PADDING_X * 2);
        int sortWidth = this.textRenderer.getWidth("Sort By: Name") + (BUTTON_PADDING_X * 2);

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Cancel"),
                button -> {
                    ClientPlayNetworking.send(CharmNCraft.COMPASS_UI_CLOSED, PacketByteBufs.empty());
                    this.close();
                }
        ).dimensions(MARGIN, this.height - buttonHeight - MARGIN, cancelWidth, buttonHeight).build());

        this.teleportButton = this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Teleport"),
                button -> this.onTeleport()
        ).dimensions(this.width - teleportWidth - MARGIN, MARGIN, teleportWidth, buttonHeight).build());

        int maxLeftButtonWidth = Math.max(Math.max(selectWidth, searchWidth), sortWidth);

        this.selectStructureButton = this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Select Structure"),
                button -> this.onSelectStructure()
        ).dimensions(MARGIN, MARGIN, maxLeftButtonWidth, buttonHeight).build());

        this.startSearchButton = this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Start Search"),
                button -> this.onStartSearch()
        ).dimensions(MARGIN, MARGIN + buttonHeight + SPACING, maxLeftButtonWidth, buttonHeight).build());

        this.sortByButton = this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Sort By: Name"),
                button -> this.onSortBy()
        ).dimensions(MARGIN, MARGIN + (buttonHeight + SPACING) * 2, maxLeftButtonWidth, buttonHeight).build());

        int searchFieldX = MARGIN + maxLeftButtonWidth + SPACING;
        int searchFieldWidth = this.width - searchFieldX - MARGIN;

        this.searchField = new TextFieldWidget(
                this.textRenderer,
                searchFieldX,
                MARGIN,
                searchFieldWidth,
                buttonHeight,
                Text.literal("Search")
        );
        this.searchField.setMaxLength(256);
        this.searchField.setPlaceholder(Text.literal("Search structures..."));
        this.addSelectableChild(this.searchField);
    }

    private void onTeleport() {
        CharmNCraft.LOGGER.info("Teleport clicked");
    }

    private void onSelectStructure() {
        CharmNCraft.LOGGER.info("Select structure clicked");
    }

    private void onStartSearch() {
        CharmNCraft.LOGGER.info("Start search clicked");
    }

    private void onSortBy() {
        CharmNCraft.LOGGER.info("Sort by clicked");
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Draw a clean dark gradient background instead of the dirt texture
        context.fillGradient(0, 0, this.width, this.height, 0xC0101010, 0xD0101010);

        // Draw search box and buttons
        this.searchField.render(context, mouseX, mouseY, delta);

        // Draw text and tooltips last (super.render handles that part)
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}