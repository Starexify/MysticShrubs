package net.nova.mysticshrubs.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.nova.mysticshrubs.init.ModItems;

public class MysticShrubBlock extends CropBlock {
    public static final int MAX_AGE = 1;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_1;

    public MysticShrubBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.MYSTICAL_SEED;
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        // Allow placement on GRASS_BLOCK and DIRT
        return pState.is(Blocks.GRASS_BLOCK) || pState.is(Blocks.DIRT);
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return false;
    }

    // On player right-click

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide) {
            if (pState.getValue(AGE).equals(1)) {
                // Set the age to 0
                pLevel.setBlock(pPos, pState.setValue(AGE, 0), 2);

                // Drop item for block state 1
                //pLevel.destroyBlock(pPos, true);
                //MysticShrubDrops.execute(pLevel, pPos.getX()+0.5, pPos.getY(), pPos.getZ()+0.5);
            }
        }
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide) {
            if (pState.getValue(AGE).equals(1)) {
                // Set the age to 0
                pLevel.setBlock(pPos, pState.setValue(AGE, 0), 2);

                // Drop item for block state 1
                //pLevel.destroyBlock(pPos, true);
                //MysticShrubDrops.execute(pLevel, pPos.getX()+0.5, pPos.getY(), pPos.getZ()+0.5);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, boolean willHarvest, FluidState fluid) {
        // Retain the values of the blocks
        boolean retainValues = super.onDestroyedByPlayer(pState, pLevel, pPos, pPlayer, willHarvest, fluid);
        if (!pLevel.isClientSide) {
            if (pState.getValue(AGE).equals(1)) {
                // Set the age to 0
                pLevel.setBlock(pPos, pState.setValue(AGE, 0), 2);

                //MysticShrubDrops.execute(pLevel, pPos.getX()+0.5, pPos.getY(), pPos.getZ()+0.5);
            }
        }
        return retainValues;
    }

    @Override
    protected float getDestroyProgress(BlockState pState, Player pPlayer, BlockGetter pLevel, BlockPos pPos) {
        if (pState.getValue(AGE) == 0) {
            return 0.05f;
        } else {
            return 1.0f;
        }
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(AGE) == 0) {
            return box(2, 0, 2, 14, 3, 14);
        } else {
            return box(2, 0, 2, 14, 14, 14);
        }
    }

    @Override
    protected boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.canSeeSky(pPos) && super.canSurvive(pState, pLevel, pPos);
    }

    @Override
    protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.isAreaLoaded(pPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        int i = this.getAge(pState);
        if (i < this.getMaxAge()) {
            float f = getGrowthSpeed(this, pLevel, pPos);
            // Adjust the growth speed here by adding a delay or reducing probability
            float modifiedGrowthSpeed = f * 0.2F; // Doubling the growth time
            if (net.neoforged.neoforge.common.CommonHooks.canCropGrow(pLevel, pPos, pState, pRandom.nextInt((int) (25.0F / modifiedGrowthSpeed) + 1) == 0)) {
                pLevel.setBlock(pPos, this.getStateForAge(i + 1), 2);
                net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(pLevel, pPos, pState);
            }
        }
    }

}
