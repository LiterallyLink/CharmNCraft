package com.charmed.charmncraft.blocks;

import com.charmed.charmncraft.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class StuffedShulkerBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final IntProperty SERVINGS = IntProperty.of("servings", 0, 4);

    public StuffedShulkerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(SERVINGS, 4));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, SERVINGS);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getStackInHand(hand);

        if (heldItem.getItem() == ModItems.SHULKER_BOWL) {
            // Consume the shulker bowl and give stuffed shulker bowl
            heldItem.decrement(1);
            ItemStack stuffedBowl = new ItemStack(ModItems.STUFFED_SHULKER_BOWL);
            if (!player.getInventory().insertStack(stuffedBowl)) {
                player.dropItem(stuffedBowl, false);
            }

            // Decrease servings
            int servings = state.get(SERVINGS);
            if (servings > 0) {
                world.setBlockState(pos, state.with(SERVINGS, servings - 1));
            } else {
                world.breakBlock(pos, false);
            }
            return ActionResult.SUCCESS;
        } else {
            // Show message "You need a Shulker Bowl to eat this"
            player.sendMessage(Text.literal("You need a Shulker Bowl to eat this"), true);
            return ActionResult.FAIL;
        }
    }
}