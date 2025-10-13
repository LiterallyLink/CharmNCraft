package com.charmed.charmncraft.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class PlayerDeathMessageMixin {

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void sendCustomDeathMessage(DamageSource damageSource, CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;

        // Get player name
        String playerName = player.getName().getString();

        // Get death reason - extract just the cause part
        String fullDeathMessage = damageSource.getDeathMessage(player).getString();
        // Remove the player name from the beginning if present
        String deathReason = fullDeathMessage.replace(playerName + " ", "");

        // Get coordinates
        BlockPos pos = player.getBlockPos();
        String coordinates = String.format("X: %d, Y: %d, Z: %d", pos.getX(), pos.getY(), pos.getZ());

        // Get dimension
        String dimension = getDimensionName(player.getWorld());

        // Create the message
        String message = String.format("[%s] %s at §a%s §fin %s",
                playerName, deathReason, coordinates, dimension);

        // Broadcast to all players
        player.getServer().getPlayerManager().broadcast(
                Text.literal(message),
                false
        );
    }

    @Inject(method = "onDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"), cancellable = true)
    private void cancelVanillaDeathMessage(DamageSource damageSource, CallbackInfo ci) {
        // This will prevent the line that broadcasts the vanilla death message
    }

    private String getDimensionName(World world) {
        String dimensionKey = world.getRegistryKey().getValue().toString();

        return switch (dimensionKey) {
            case "minecraft:overworld" -> "Overworld";
            case "minecraft:the_nether" -> "The Nether";
            case "minecraft:the_end" -> "The End";
            default -> {
                // For modded dimensions, format them nicely
                String[] parts = dimensionKey.split(":");
                if (parts.length == 2) {
                    // Capitalize and replace underscores: "twilightforest:twilight_forest" -> "Twilight Forest"
                    String dimensionName = parts[1].replace("_", " ");
                    yield capitalizeWords(dimensionName);
                }
                yield dimensionKey;
            }
        };
    }

    private String capitalizeWords(String str) {
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return result.toString().trim();
    }
}