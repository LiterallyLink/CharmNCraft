package com.charmed.charmncraft.item;

import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;

public class NetheriteHorseArmorItem extends HorseArmorItem {
    private static final int PROTECTION = 13; // Higher than diamond (11)

    public NetheriteHorseArmorItem(Settings settings) {
        super(PROTECTION, "netherite", settings);
    }

    @Override
    public boolean isFireproof() {
        return true;
    }
}