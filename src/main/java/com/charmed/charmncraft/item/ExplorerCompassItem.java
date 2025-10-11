package com.charmed.charmncraft.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ExplorerCompassItem extends Item {

    public ExplorerCompassItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        // Only open GUI on client side
        if (world.isClient) {
            openGui();
        }

        return TypedActionResult.success(stack);
    }

    @Environment(EnvType.CLIENT)
    private void openGui() {
        com.charmed.charmncraft.client.ClientHelper.openExplorerCompassGui();
    }
}