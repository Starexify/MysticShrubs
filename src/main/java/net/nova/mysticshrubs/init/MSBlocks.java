package net.nova.mysticshrubs.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.mysticshrubs.block.MysticShrubBlock;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

public class MSBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final DeferredBlock<Block> MYSTIC_SHRUB = BLOCKS.registerBlock("mystic_shrub", MysticShrubBlock::new, BlockBehaviour.Properties.of()
            .noCollission()
            .noOcclusion()
            .sound(SoundType.GRASS)
            .lightLevel(light -> 5)
    );
}
