package net.nova.mystic_shrubs.data.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.nova.mystic_shrubs.MysticShrubs;

import java.util.List;

public class MSPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MYSTIC_SHRUB_PLACED = registerKey("mystic_shrub_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(MYSTIC_SHRUB_PLACED, new PlacedFeature(configuredFeatures.getOrThrow(MSConfiguredFeatures.MYSTIC_SHRUB_PATCH),
                List.of(RarityFilter.onAverageOnceEvery(4), CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())
        ));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, MysticShrubs.rl(name));
    }
}