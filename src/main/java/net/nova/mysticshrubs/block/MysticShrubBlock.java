package net.nova.mysticshrubs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
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
import net.neoforged.neoforge.common.CommonHooks;
import net.nova.mysticshrubs.init.MSItems;

public class MysticShrubBlock extends CropBlock {
    public static final int MAX_AGE = 1;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_1;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(2, 0, 2, 14, 3, 14),
            Block.box(1.5, 0, 1.5, 14.5, 14, 14.5)
    };

    public MysticShrubBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), Integer.valueOf(0)));
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(Blocks.GRASS_BLOCK) || pState.is(Blocks.DIRT);
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
    protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.isAreaLoaded(pPos, 1)) return;
        int i = this.getAge(pState);
        if (i < this.getMaxAge()) {
            float f = getGrowthSpeed(pState, pLevel, pPos);
            if (CommonHooks.canCropGrow(pLevel, pPos, pState, pRandom.nextInt((int) (25.0F / f) + 1) == 0)) {
                pLevel.setBlock(pPos, this.getStateForAge(i + 1), 2);
                CommonHooks.fireCropGrowPost(pLevel, pPos, pState);
            }
        }
    }

    @Override
    protected boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.canSeeSky(pPos) && super.canSurvive(pState, pLevel, pPos);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return MSItems.MYSTICAL_SEED;
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return false;
    }

    // On player interactions drops
    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide) {
            if (pState.getValue(AGE).equals(1)) {
                // Drop item for age 1 and set the age to 0
                pLevel.destroyBlock(pPos, true);
                pLevel.setBlock(pPos, pState.setValue(AGE, 0), 2);

                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, boolean willHarvest, FluidState fluid) {
        if (!pLevel.isClientSide) {
            if (pState.getValue(AGE).equals(1)) {
                // Drop item for age 1 and set the age to 0
                pLevel.setBlock(pPos, pState.setValue(AGE, 0), 3);
            }
        }
        return false;
    }

    // Slower destroy speed for age 0
    @Override
    protected float getDestroyProgress(BlockState pState, Player pPlayer, BlockGetter pLevel, BlockPos pPos) {
        if (pState.getValue(AGE) == 0) {
            return 0.05f;
        } else {
            return 1.0f;
        }
    }

    // Rendering speed
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE_BY_AGE[this.getAge(pState)];
    }
}
