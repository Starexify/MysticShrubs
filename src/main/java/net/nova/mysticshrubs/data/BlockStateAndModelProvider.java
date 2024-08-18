package net.nova.mysticshrubs.data;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.mysticshrubs.init.MSBlocks;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

public class BlockStateAndModelProvider extends BlockStateProvider {
    public BlockStateAndModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        mysticShrub(MSBlocks.MYSTIC_SHRUB.get(), BlockStateProperties.AGE_1, 0, 1);
    }

    private void mysticShrub(Block pCropBlock, IntegerProperty pAgeProperty, int... pAgeToVisualStageMapping) {
        if (pAgeProperty.getPossibleValues().size() != pAgeToVisualStageMapping.length) {
            throw new IllegalArgumentException("Number of ages and visual stages must match");
        }

        getVariantBuilder(pCropBlock)
                .forAllStates(state -> {
                    int age = state.getValue(pAgeProperty);
                    String stageName = "stage" + pAgeToVisualStageMapping[age];
                    return ConfiguredModel.builder()
                            .modelFile(models().cross(name(pCropBlock) + "_" + stageName, modLoc("block/" + name(pCropBlock) + "_" + stageName)).renderType(RenderType.CUTOUT.name))
                            .build();
                });

        itemModels().getBuilder(name(pCropBlock))
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", modLoc("block/" + name(pCropBlock) + "_stage1"));
    }

    // Other stuff
    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }
}
