package com.charmed.charmncraft;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ExplorersCompassItem extends Item {

    public ExplorersCompassItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (!world.isClient) {
            player.sendMessage(Text.literal("Explorer's Compass activated!"), false);

            ServerPlayNetworking.send(
                    (ServerPlayerEntity) player,
                    new Identifier(CharmNCraft.MOD_ID, "open_compass_screen"),
                    PacketByteBufs.empty()
            );
        }

        return TypedActionResult.success(itemStack, world.isClient);
    }
}