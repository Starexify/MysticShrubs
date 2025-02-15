package net.nova.mysticshrubs.data;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.nova.mysticshrubs.init.MSBlocks;
import net.nova.mysticshrubs.init.MSItems;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

public class MSModelProvider extends ModelProvider {
    public MSModelProvider(PackOutput output) {
        super(output, MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(MSItems.EMERALD_SHARD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MSItems.EMERALD_PIECE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MSItems.HEART_DROP.get(), ModelTemplates.FLAT_ITEM);

        blockModels.createCropBlock(MSBlocks.MYSTIC_SHRUB.get(), BlockStateProperties.AGE_1, 0, 1);
    }
}
