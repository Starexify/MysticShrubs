package net.nova.mystic_shrubs.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.nova.mystic_shrubs.MysticShrubs;
import net.nova.mystic_shrubs.init.MSBlocks;

import static net.nova.mystic_shrubs.block.MysticShrubBlock.AGE;

public class MSConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> MYSTIC_SHRUB_PATCH = registerKey("mystic_shrub");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(MSConfiguredFeatures.MYSTIC_SHRUB_PATCH,
                new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(12, 2, 0,
                        PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(MSBlocks.MYSTIC_SHRUB.defaultBlockState().setValue(AGE, 1))), PlacementUtils.HEIGHTMAP_WORLD_SURFACE))
                ));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, MysticShrubs.rl(name));
    }
}