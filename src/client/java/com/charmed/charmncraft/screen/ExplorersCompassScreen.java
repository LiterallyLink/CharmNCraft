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

    private int cancelButtonX, cancelButtonY;
    private int teleportButtonX, teleportButtonY;
    private int selectStructureButtonX, selectStructureButtonY;
    private int startSearchButtonX, startSearchButtonY;
    private int sortByButtonX, sortByButtonY;

    private TextFieldWidget searchField;
    private StructureListWidget structureList;
    private String selectedStructure = null;

    public ExplorersCompassScreen() {
        super(Text.literal("Explorer's Compass"));
    }

    @Override
    protected void init() {
        super.init();

        cancelButtonX = BUTTON_MARGIN;
        cancelButtonY = this.height - BUTTON_HEIGHT - BUTTON_MARGIN;

        teleportButtonX = this.width - BUTTON_WIDTH - BUTTON_MARGIN;
        teleportButtonY = BUTTON_MARGIN;

        selectStructureButtonX = BUTTON_MARGIN;
        selectStructureButtonY = BUTTON_MARGIN;

        startSearchButtonX = BUTTON_MARGIN;
        startSearchButtonY = selectStructureButtonY + BUTTON_HEIGHT + BUTTON_SPACING;

        sortByButtonX = BUTTON_MARGIN;
        sortByButtonY = startSearchButtonY + BUTTON_HEIGHT + BUTTON_SPACING;

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

        int listY = searchFieldY + BUTTON_HEIGHT + BUTTON_SPACING;
        int listHeight = this.height - listY - BUTTON_MARGIN;
        int listWidth = 200;
        this.structureList = new StructureListWidget(
                this.client,
                listWidth,
                listHeight,
                listY,
                searchFieldX,
                18
        );

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

        renderButton(context, selectStructureButtonX, selectStructureButtonY, "Select Structure");
        renderButton(context, startSearchButtonX, startSearchButtonY, "Start Search");
        renderButton(context, sortByButtonX, sortByButtonY, "Sort By: Name");
        renderButton(context, cancelButtonX, cancelButtonY, "Cancel");
        renderButton(context, teleportButtonX, teleportButtonY, "Teleport");

        this.searchField.render(context, mouseX, mouseY, delta);
        this.structureList.render(context, mouseX, mouseY, delta);

        super.render(context, mouseX, mouseY, delta);
    }

    private void renderButton(DrawContext context, int x, int y, String label) {
        context.fill(x, y, x + BUTTON_WIDTH, y + BUTTON_HEIGHT, 0xFF000000);
        context.drawBorder(x, y, BUTTON_WIDTH, BUTTON_HEIGHT, 0xFFAAAAAA);

        Text text = Text.literal(label);
        int textX = x + (BUTTON_WIDTH - this.textRenderer.getWidth(text)) / 2;
        int textY = y + (BUTTON_HEIGHT - this.textRenderer.fontHeight) / 2;
        context.drawText(this.textRenderer, text, textX, textY, 0xFFFFFF, false);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isButtonClicked(mouseX, mouseY, cancelButtonX, cancelButtonY)) {
            playClickSound();
            ClientPlayNetworking.send(CharmNCraft.COMPASS_UI_CLOSED, PacketByteBufs.empty());
            this.close();
            return true;
        }

        if (isButtonClicked(mouseX, mouseY, teleportButtonX, teleportButtonY)) {
            playClickSound();
            return true;
        }

        if (isButtonClicked(mouseX, mouseY, selectStructureButtonX, selectStructureButtonY)) {
            playClickSound();
            return true;
        }

        if (isButtonClicked(mouseX, mouseY, startSearchButtonX, startSearchButtonY)) {
            playClickSound();
            return true;
        }

        if (isButtonClicked(mouseX, mouseY, sortByButtonX, sortByButtonY)) {
            playClickSound();
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean isButtonClicked(double mouseX, double mouseY, int buttonX, int buttonY) {
        return mouseX >= buttonX && mouseX <= buttonX + BUTTON_WIDTH &&
                mouseY >= buttonY && mouseY <= buttonY + BUTTON_HEIGHT;
    }

    private void playClickSound() {
        this.client.getSoundManager().play(
                PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F)
        );
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
            this.setLeftPos(left);
            this.setRenderBackground(false);
            this.setRenderHorizontalShadows(false);
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
            return this.listX + 2;
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
                ExplorersCompassScreen.this.selectedStructure =
                        this.structureKey != null ? this.structureKey.getValue().toString() : this.displayName;
                playClickSound();
                return true;
            }

            @Override
            public Text getNarration() {
                return Text.literal(this.displayName);
            }
        }
    }
}