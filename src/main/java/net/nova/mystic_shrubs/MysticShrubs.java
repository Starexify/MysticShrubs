package net.nova.mystic_shrubs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.nova.mystic_shrubs.init.MSBlocks;
import net.nova.mystic_shrubs.init.MSItems;
import net.nova.mystic_shrubs.init.Sounds;
import net.nova.mystic_shrubs.worldgen.MSPlacedFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysticShrubs implements ModInitializer {
    public static final String MODID = "mystic_shrubs";
    public static final Logger LOGGER = LoggerFactory.getLogger(MysticShrubs.class);

    @Override
    public void onInitialize() {
        Sounds.initialize();
        MSBlocks.initialize();
        MSItems.initialize();

        // Biome Modifiers
        BiomeModifications.addFeature(BiomeSelectors.tag(CAN_PLACE_MYSTIC_SHRUBS), GenerationStep.Decoration.VEGETAL_DECORATION, MSPlacedFeatures.MYSTIC_SHRUB_PLACED);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public static final TagKey<Biome> CAN_PLACE_MYSTIC_SHRUBS = TagKey.create(Registries.BIOME, MysticShrubs.rl("can_place_mystic_shrubs"));
}
