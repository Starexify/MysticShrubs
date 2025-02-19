package net.nova.mystic_shrubs.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.nova.mystic_shrubs.data.worldgen.MSBiomeModifiers;
import net.nova.mystic_shrubs.data.worldgen.MSConfiguredFeatures;
import net.nova.mystic_shrubs.data.worldgen.MSPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static net.nova.mystic_shrubs.MysticShrubs.MODID;

public class DatapackProvider extends DatapackBuiltinEntriesProvider {
    public DatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, new RegistrySetBuilder()
                        .add(Registries.CONFIGURED_FEATURE, MSConfiguredFeatures::bootstrap)
                        .add(Registries.PLACED_FEATURE, MSPlacedFeatures::bootstrap)
                        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, MSBiomeModifiers::bootstrap),
                Set.of(MODID));
    }
}
