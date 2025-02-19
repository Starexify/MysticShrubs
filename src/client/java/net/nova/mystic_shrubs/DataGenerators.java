package net.nova.mystic_shrubs;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.nova.mystic_shrubs.data.*;
import net.nova.mystic_shrubs.data.BlockLootTables;
import net.nova.mystic_shrubs.worldgen.MSConfiguredFeatures;
import net.nova.mystic_shrubs.worldgen.MSPlacedFeatures;

public class DataGenerators implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(LangProvider::new);
        pack.addProvider(BlockLootTables::new);
        pack.addProvider(MSBiomeTagsProvider::new);
        pack.addProvider(MSWorldgenGenerator::new);
        pack.addProvider(MSModelProvider::new);
        pack.addProvider(SoundsProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, MSConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, MSPlacedFeatures::bootstrap);
    }
}