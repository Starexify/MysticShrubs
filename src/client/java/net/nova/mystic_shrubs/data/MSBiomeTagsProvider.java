package net.nova.mystic_shrubs.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.nova.mystic_shrubs.MysticShrubs;

import java.util.concurrent.CompletableFuture;

public class MSBiomeTagsProvider extends FabricTagProvider<Biome> {
    public MSBiomeTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BIOME, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(MysticShrubs.CAN_PLACE_MYSTIC_SHRUBS).addTag(ConventionalBiomeTags.IS_PLAINS).addTag(BiomeTags.IS_SAVANNA).addTag(BiomeTags.IS_RIVER);
    }
}