package net.nova.mysticshrubs.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.Tags;
import net.nova.mysticshrubs.MysticShrubs;

import java.util.concurrent.CompletableFuture;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

public class MSBiomeTagsProvider extends BiomeTagsProvider {
    public MSBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256485_) {
        tag(MysticShrubs.CAN_PLACE_MYSTIC_SHRUBS).addTags(Tags.Biomes.IS_PLAINS, BiomeTags.IS_SAVANNA, BiomeTags.IS_RIVER);
    }
}
