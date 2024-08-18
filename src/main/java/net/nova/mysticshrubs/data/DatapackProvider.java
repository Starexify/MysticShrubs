package net.nova.mysticshrubs.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.nova.mysticshrubs.data.worldgen.MSBiomeModifiers;
import net.nova.mysticshrubs.data.worldgen.MSConfiguredFeatures;
import net.nova.mysticshrubs.data.worldgen.MSPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

public class DatapackProvider extends DatapackBuiltinEntriesProvider {
    public DatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, new RegistrySetBuilder()
                        .add(Registries.CONFIGURED_FEATURE, MSConfiguredFeatures::bootstrap)
                        .add(Registries.PLACED_FEATURE, MSPlacedFeatures::bootstrap)
                        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, MSBiomeModifiers::bootstrap),
                Set.of(MODID));
    }
}
