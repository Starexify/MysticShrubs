package net.nova.mysticshrubs.data;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.nova.mysticshrubs.init.MSBlocks;
import net.nova.mysticshrubs.init.MSItems;
import net.nova.mysticshrubs.init.Sounds;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

public class LangProvider extends LanguageProvider {
    public LangProvider(PackOutput output) {
        super(output, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItem(MSItems.EMERALD_SHARD, "Emerald Shard");
        addItem(MSItems.EMERALD_PIECE, "Emerald Piece");
        addItem(MSItems.HEART_DROP, "Heart Drop");
        addItem(MSItems.MYSTICAL_SEED, "Mystical Seed");

        addBlock(MSBlocks.MYSTIC_SHRUB, "Mystic Shrub");

        // Sounds
        add(SoundsProvider.getSubtitle(Sounds.EMERALD_SHARD_PICKUP), "Picked up Emerald Shard");
        add(SoundsProvider.getSubtitle(Sounds.EMERALD_SHARD_USED), "Emerald Shard used");
        add(SoundsProvider.getSubtitle(Sounds.EMERALD_PIECE_USED), "Emerald Piece used");
        add(SoundsProvider.getSubtitle(Sounds.COLLECT_HEART), "Collected Heart");
    }
}
