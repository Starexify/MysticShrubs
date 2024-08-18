package net.nova.mysticshrubs.data;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.mysticshrubs.init.MSItems;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

public class MSItemModelProvider extends ItemModelProvider {
    public MSItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(MSItems.EMERALD_SHARD.get());
        basicItem(MSItems.EMERALD_PIECE.get());
        basicItem(MSItems.HEART_DROP.get());
        basicItem(MSItems.MYSTICAL_SEED.get());
    }
}
