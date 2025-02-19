package net.nova.mystic_shrubs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
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
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.nova.mystic_shrubs.init.MSItems;

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
    protected boolean mayPlaceOn(BlockState pState, BlockGetter level, BlockPos pos) {
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
    protected void randomTick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource random) {
        int i = this.getAge(state);
        if (i < this.getMaxAge()) {
            float f = getGrowthSpeed(state, serverLevel, pos);
            if (CommonHooks.canCropGrow(serverLevel, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0)) {
                serverLevel.setBlock(pos, getStateForAge(i + 1), 2);
                CommonHooks.fireCropGrowPost(serverLevel, pos, state);
                serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(getStateForAge(i + 1)));
            }
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.canSeeSky(pos) && super.canSurvive(state, level, pos);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return MSItems.MYSTICAL_SEED;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return false;
    }

    // On player interactions drops
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (state.getValue(AGE).equals(1)) {
            level.destroyBlock(pos, true);
            BlockState smallMysticShrub = state.setValue(AGE, 0);
            level.setBlock(pos, smallMysticShrub, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, smallMysticShrub));
            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (!level.isClientSide && state.getValue(AGE).equals(1)) {
            super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
            BlockState smallMysticShrub = state.setValue(AGE, 0);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, smallMysticShrub));
            return level.setBlock(pos, smallMysticShrub, 3);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    // Slower destroy speed for age 0
    @Override
    protected float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        if (state.getValue(AGE) == 0) {
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
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext pContext) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }
}
