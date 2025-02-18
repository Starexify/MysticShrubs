package net.nova.mysticshrubs.data;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.nova.mysticshrubs.init.MSBlocks;
import net.nova.mysticshrubs.init.MSItems;

public class MSModelProvider extends FabricModelProvider {
    public MSModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createCropBlock(MSBlocks.MYSTIC_SHRUB, BlockStateProperties.AGE_1, 0, 1);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(MSItems.EMERALD_SHARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MSItems.EMERALD_PIECE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MSItems.HEART_DROP, ModelTemplates.FLAT_ITEM);
    }
}
