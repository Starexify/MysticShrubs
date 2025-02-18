package net.nova.mysticshrubs.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.nova.mysticshrubs.MysticShrubs;

import java.util.List;

public class MSPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MYSTIC_SHRUB_PLACED = registerKey("mystic_shrub_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(MYSTIC_SHRUB_PLACED, new PlacedFeature(configuredFeatures.getOrThrow(MSConfiguredFeatures.MYSTIC_SHRUB_PATCH),
                List.copyOf(VegetationPlacements.worldSurfaceSquaredWithCount(2))
        ));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, MysticShrubs.rl(name));
    }
}