package com.charmed.charmncraft.item;

import net.minecraft.item.HorseArmorItem;
import net.minecraft.util.Identifier;

public class NetheriteHorseArmorItem extends HorseArmorItem {
    private static final int PROTECTION = 13; // Higher than diamond (11)
    private static final Identifier TEXTURE = new Identifier("charmncraft", "textures/entity/horse/armor/horse_armor_netherite.png");

    public NetheriteHorseArmorItem(Settings settings) {
        super(PROTECTION, "netherite", settings);
    }

    @Override
    public Identifier getEntityTexture() {
        return TEXTURE;
    }

    @Override
    public boolean isFireproof() {
        return true;
    }
}