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
    private String selectedStructure = null;

    public ExplorersCompassScreen() {
        super(Text.literal("Explorer's Compass"));
    }

    @Override
    protected void init() {
        super.init();

        int cancelWidth = this.textRenderer.getWidth("Cancel") + (BUTTON_PADDING_X * 2);
        int cancelHeight = this.textRenderer.fontHeight + (BUTTON_PADDING_Y * 2);

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Cancel"),
                button -> {
                    ClientPlayNetworking.send(CharmNCraft.COMPASS_UI_CLOSED, PacketByteBufs.empty());
                    this.close();
                }
        ).dimensions(
                MARGIN,
                this.height - cancelHeight - MARGIN,
                cancelWidth,
                cancelHeight
        ).build());

        int teleportWidth = this.textRenderer.getWidth("Teleport") + (BUTTON_PADDING_X * 2);
        int selectWidth = this.textRenderer.getWidth("Select Structure") + (BUTTON_PADDING_X * 2);
        int searchWidth = this.textRenderer.getWidth("Start Search") + (BUTTON_PADDING_X * 2);
        int sortWidth = this.textRenderer.getWidth("Sort By: Name") + (BUTTON_PADDING_X * 2);
        int buttonHeight = this.textRenderer.fontHeight + (BUTTON_PADDING_Y * 2);

        this.teleportButton = this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Teleport"),
                button -> this.onTeleport()
        ).dimensions(
                this.width - teleportWidth - MARGIN,
                MARGIN,
                teleportWidth,
                buttonHeight
        ).build());

        this.selectStructureButton = this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Select Structure"),
                button -> this.onSelectStructure()
        ).dimensions(
                MARGIN,
                MARGIN,
                selectWidth,
                buttonHeight
        ).build());

        this.startSearchButton = this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Start Search"),
                button -> this.onStartSearch()
        ).dimensions(
                MARGIN,
                MARGIN + buttonHeight + SPACING,
                searchWidth,
                buttonHeight
        ).build());

        this.sortByButton = this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Sort By: Name"),
                button -> this.onSortBy()
        ).dimensions(
                MARGIN,
                MARGIN + (buttonHeight + SPACING) * 2,
                sortWidth,
                buttonHeight
        ).build());

        int maxLeftButtonWidth = Math.max(Math.max(selectWidth, searchWidth), sortWidth);
        int searchFieldX = MARGIN + maxLeftButtonWidth + SPACING;
        int searchFieldY = MARGIN;
        int searchFieldWidth = 400;

        this.searchField = new TextFieldWidget(
                this.textRenderer,
                searchFieldX,
                searchFieldY,
                searchFieldWidth,
                buttonHeight,
                Text.literal("Search")
        );
        this.searchField.setMaxLength(256);
        this.searchField.setPlaceholder(Text.literal("Search structures..."));
        this.addSelectableChild(this.searchField);

        this.updateButtonStates();
    }

    private void updateButtonStates() {
        boolean hasSelection = this.selectedStructure != null;
        this.teleportButton.active = hasSelection;
        this.startSearchButton.active = hasSelection;
    }

    private void onTeleport() {
        if (this.selectedStructure != null) {
            CharmNCraft.LOGGER.info("Teleporting to: {}", this.selectedStructure);
        }
    }

    private void onSelectStructure() {
        CharmNCraft.LOGGER.info("Select structure clicked");
    }

    private void onStartSearch() {
        if (this.selectedStructure != null) {
            CharmNCraft.LOGGER.info("Starting search for: {}", this.selectedStructure);
        }
    }

    private void onSortBy() {
        CharmNCraft.LOGGER.info("Sort by clicked");
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fillGradient(0, 0, this.width, this.height, 0xC0101010, 0xD0101010);
        super.render(context, mouseX, mouseY, delta);
        this.searchField.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}