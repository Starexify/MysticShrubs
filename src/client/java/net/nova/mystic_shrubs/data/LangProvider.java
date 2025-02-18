package net.nova.mystic_shrubs.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.nova.mystic_shrubs.init.MSBlocks;
import net.nova.mystic_shrubs.init.MSItems;
import net.nova.mystic_shrubs.init.Sounds;

import java.util.concurrent.CompletableFuture;

public class LangProvider extends FabricLanguageProvider {
    public LangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        translationBuilder.add(MSItems.EMERALD_SHARD, "Emerald Shard");
        translationBuilder.add(MSItems.EMERALD_PIECE, "Emerald Piece");
        translationBuilder.add(MSItems.HEART_DROP, "Heart Drop");
        translationBuilder.add(MSItems.MYSTICAL_SEED, "Mystical Seed");

        translationBuilder.add(MSBlocks.MYSTIC_SHRUB, "Mystic Shrub");

        // Sounds
        translationBuilder.add(SoundsProvider.getSubtitle(Sounds.EMERALD_SHARD_PICKUP), "Picked up Emerald Shard");
        translationBuilder.add(SoundsProvider.getSubtitle(Sounds.EMERALD_SHARD_USED), "Emerald Shard used");
        translationBuilder.add(SoundsProvider.getSubtitle(Sounds.EMERALD_PIECE_USED), "Emerald Piece used");
        translationBuilder.add(SoundsProvider.getSubtitle(Sounds.COLLECT_HEART), "Collected Heart");
    }
}
