package com.charmed.charmncraft.screen;

import com.charmed.charmncraft.CharmNCraft;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.gen.structure.Structure;

public class ExplorersCompassScreen extends Screen {
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_MARGIN = 10;
    private static final int BUTTON_SPACING = 5;

    private int cancelButtonX;
    private int cancelButtonY;
    private int teleportButtonX;
    private int teleportButtonY;

    private int selectStructureButtonX;
    private int selectStructureButtonY;
    private int startSearchButtonX;
    private int startSearchButtonY;
    private int sortByButtonX;
    private int sortByButtonY;

    private TextFieldWidget searchField;
    private StructureListWidget structureList;
    private String selectedStructure = null;

    public ExplorersCompassScreen() {
        super(Text.literal("Explorer's Compass"));
    }

    @Override
    protected void init() {
        super.init();

        this.cancelButtonX = BUTTON_MARGIN;
        this.cancelButtonY = this.height - BUTTON_HEIGHT - BUTTON_MARGIN;

        this.teleportButtonX = this.width - BUTTON_WIDTH - BUTTON_MARGIN;
        this.teleportButtonY = BUTTON_MARGIN;

        this.selectStructureButtonX = BUTTON_MARGIN;
        this.selectStructureButtonY = BUTTON_MARGIN;

        this.startSearchButtonX = BUTTON_MARGIN;
        this.startSearchButtonY = selectStructureButtonY + BUTTON_HEIGHT + BUTTON_SPACING;

        this.sortByButtonX = BUTTON_MARGIN;
        this.sortByButtonY = startSearchButtonY + BUTTON_HEIGHT + BUTTON_SPACING;

        int searchFieldX = selectStructureButtonX + BUTTON_WIDTH + BUTTON_SPACING;
        int searchFieldY = selectStructureButtonY;
        this.searchField = new TextFieldWidget(
                this.textRenderer,
                searchFieldX,
                searchFieldY,
                400,
                BUTTON_HEIGHT,
                Text.literal("Search")
        );
        this.searchField.setMaxLength(256);
        this.searchField.setPlaceholder(Text.literal("Search"));
        this.addSelectableChild(this.searchField);

        int listX = searchFieldX;
        int listY = searchFieldY + BUTTON_HEIGHT + BUTTON_SPACING;
        int listWidth = 400;
        int listHeight = this.height - listY - BUTTON_MARGIN;

        this.structureList = new StructureListWidget(
                this.client,
                listWidth,
                listHeight,
                listY,
                listX,
                18
        );

        // Test data
        this.structureList.addTestEntry("Aurora Palace");
        this.structureList.addTestEntry("Bastion Remnant");
        this.structureList.addTestEntry("Buried Treasure");
        this.structureList.addTestEntry("Courtyard");
        this.structureList.addTestEntry("Dark Tower");
        this.structureList.addTestEntry("Desert Pyramid");

        this.addSelectableChild(this.structureList);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fillGradient(0, 0, this.width, this.height, 0xC0101010, 0xD0101010);

        context.fill(selectStructureButtonX, selectStructureButtonY,
                selectStructureButtonX + BUTTON_WIDTH, selectStructureButtonY + BUTTON_HEIGHT, 0xFF000000);
        context.drawBorder(selectStructureButtonX, selectStructureButtonY, BUTTON_WIDTH, BUTTON_HEIGHT, 0xFFAAAAAA);
        Text selectText = Text.literal("Select Structure");
        int selectTextWidth = this.textRenderer.getWidth(selectText);
        int selectTextX = selectStructureButtonX + (BUTTON_WIDTH - selectTextWidth) / 2;
        int selectTextY = selectStructureButtonY + (BUTTON_HEIGHT - this.textRenderer.fontHeight) / 2;
        context.drawText(this.textRenderer, selectText, selectTextX, selectTextY, 0xFFFFFF, false);

        context.fill(startSearchButtonX, startSearchButtonY,
                startSearchButtonX + BUTTON_WIDTH, startSearchButtonY + BUTTON_HEIGHT, 0xFF000000);
        context.drawBorder(startSearchButtonX, startSearchButtonY, BUTTON_WIDTH, BUTTON_HEIGHT, 0xFFAAAAAA);
        Text startText = Text.literal("Start Search");
        int startTextWidth = this.textRenderer.getWidth(startText);
        int startTextX = startSearchButtonX + (BUTTON_WIDTH - startTextWidth) / 2;
        int startTextY = startSearchButtonY + (BUTTON_HEIGHT - this.textRenderer.fontHeight) / 2;
        context.drawText(this.textRenderer, startText, startTextX, startTextY, 0xFFFFFF, false);

        context.fill(sortByButtonX, sortByButtonY,
                sortByButtonX + BUTTON_WIDTH, sortByButtonY + BUTTON_HEIGHT, 0xFF000000);
        context.drawBorder(sortByButtonX, sortByButtonY, BUTTON_WIDTH, BUTTON_HEIGHT, 0xFFAAAAAA);
        Text sortText = Text.literal("Sort By: Name");
        int sortTextWidth = this.textRenderer.getWidth(sortText);
        int sortTextX = sortByButtonX + (BUTTON_WIDTH - sortTextWidth) / 2;
        int sortTextY = sortByButtonY + (BUTTON_HEIGHT - this.textRenderer.fontHeight) / 2;
        context.drawText(this.textRenderer, sortText, sortTextX, sortTextY, 0xFFFFFF, false);

        context.fill(cancelButtonX, cancelButtonY,
                cancelButtonX + BUTTON_WIDTH, cancelButtonY + BUTTON_HEIGHT, 0xFF000000);
        context.drawBorder(cancelButtonX, cancelButtonY, BUTTON_WIDTH, BUTTON_HEIGHT, 0xFFAAAAAA);
        Text cancelText = Text.literal("Cancel");
        int cancelTextWidth = this.textRenderer.getWidth(cancelText);
        int cancelTextX = cancelButtonX + (BUTTON_WIDTH - cancelTextWidth) / 2;
        int cancelTextY = cancelButtonY + (BUTTON_HEIGHT - this.textRenderer.fontHeight) / 2;
        context.drawText(this.textRenderer, cancelText, cancelTextX, cancelTextY, 0xFFFFFF, false);

        context.fill(teleportButtonX, teleportButtonY,
                teleportButtonX + BUTTON_WIDTH, teleportButtonY + BUTTON_HEIGHT, 0xFF000000);
        context.drawBorder(teleportButtonX, teleportButtonY, BUTTON_WIDTH, BUTTON_HEIGHT, 0xFFAAAAAA);
        Text teleportText = Text.literal("Teleport");
        int teleportTextWidth = this.textRenderer.getWidth(teleportText);
        int teleportTextX = teleportButtonX + (BUTTON_WIDTH - teleportTextWidth) / 2;
        int teleportTextY = teleportButtonY + (BUTTON_HEIGHT - this.textRenderer.fontHeight) / 2;
        context.drawText(this.textRenderer, teleportText, teleportTextX, teleportTextY, 0xFFFFFF, false);

        this.searchField.render(context, mouseX, mouseY, delta);
        this.structureList.render(context, mouseX, mouseY, delta);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (mouseX >= cancelButtonX && mouseX <= cancelButtonX + BUTTON_WIDTH &&
                mouseY >= cancelButtonY && mouseY <= cancelButtonY + BUTTON_HEIGHT) {

            this.client.getSoundManager().play(
                    PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F)
            );

            ClientPlayNetworking.send(CharmNCraft.COMPASS_UI_CLOSED, PacketByteBufs.empty());
            this.close();
            return true;
        }

        if (mouseX >= teleportButtonX && mouseX <= teleportButtonX + BUTTON_WIDTH &&
                mouseY >= teleportButtonY && mouseY <= teleportButtonY + BUTTON_HEIGHT) {

            this.client.getSoundManager().play(
                    PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F)
            );
            return true;
        }

        if (mouseX >= selectStructureButtonX && mouseX <= selectStructureButtonX + BUTTON_WIDTH &&
                mouseY >= selectStructureButtonY && mouseY <= selectStructureButtonY + BUTTON_HEIGHT) {

            this.client.getSoundManager().play(
                    PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F)
            );
            return true;
        }

        if (mouseX >= startSearchButtonX && mouseX <= startSearchButtonX + BUTTON_WIDTH &&
                mouseY >= startSearchButtonY && mouseY <= startSearchButtonY + BUTTON_HEIGHT) {

            this.client.getSoundManager().play(
                    PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F)
            );
            return true;
        }

        if (mouseX >= sortByButtonX && mouseX <= sortByButtonX + BUTTON_WIDTH &&
                mouseY >= sortByButtonY && mouseY <= sortByButtonY + BUTTON_HEIGHT) {

            this.client.getSoundManager().play(
                    PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F)
            );
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    private class StructureListWidget extends AlwaysSelectedEntryListWidget<StructureListWidget.StructureEntry> {
        private final int listX;

        public StructureListWidget(net.minecraft.client.MinecraftClient client, int width, int height, int top, int left, int itemHeight) {
            super(client, width, height, top, top + height, itemHeight);
            this.listX = left;
        }

        public void addStructure(RegistryKey<Structure> structureKey) {
            this.addEntry(new StructureEntry(structureKey));
        }

        public void addTestEntry(String name) {
            this.addEntry(new StructureEntry(name));
        }

        @Override
        public int getRowWidth() {
            return this.width - 10;
        }

        @Override
        protected int getScrollbarPositionX() {
            return this.listX + this.width - 6;
        }

        @Override
        public int getRowLeft() {
            return this.listX;
        }

        public class StructureEntry extends AlwaysSelectedEntryListWidget.Entry<StructureEntry> {
            private final RegistryKey<Structure> structureKey;
            private final String displayName;

            public StructureEntry(RegistryKey<Structure> structureKey) {
                this.structureKey = structureKey;
                this.displayName = "Test Structure";
            }

            public StructureEntry(String name) {
                this.structureKey = null;
                this.displayName = name;
            }

            @Override
            public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
                if (hovered) {
                    context.fill(x, y, x + entryWidth, y + entryHeight, 0x80FFFFFF);
                }

                context.drawText(
                        ExplorersCompassScreen.this.textRenderer,
                        this.displayName,
                        x + 2,
                        y + 2,
                        0xFFFFFF,
                        false
                );
            }

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int button) {
                StructureListWidget.this.setSelected(this);
                if (this.structureKey != null) {
                    ExplorersCompassScreen.this.selectedStructure = this.structureKey.getValue().toString();
                } else {
                    ExplorersCompassScreen.this.selectedStructure = this.displayName;
                }
                ExplorersCompassScreen.this.client.getSoundManager().play(
                        PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F)
                );
                return true;
            }

            @Override
            public Text getNarration() {
                return Text.literal(this.displayName);
            }
        }
    }
}