package net.nova.mysticshrubs.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.nova.mysticshrubs.MysticShrubs;
import net.nova.mysticshrubs.block.MysticShrubBlock;

import java.util.function.Function;

public class MSBlocks {
    public static final Block MYSTIC_SHRUB = registerBlock("mystic_shrub", MysticShrubBlock::new, BlockBehaviour.Properties.of()
            .noCollission()
            .noOcclusion()
            .sound(SoundType.GRASS)
            .lightLevel(light -> 5)
    );

    // Registers
    public static <T extends Block> T registerBlock(String name, Function<BlockBehaviour.Properties, T> factory, BlockBehaviour.Properties settings) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, MysticShrubs.rl(name));
        return Registry.register(BuiltInRegistries.BLOCK, key, factory.apply(settings.setId(key)));
    }

    public static void initialize() {
        MysticShrubs.LOGGER.info("Registering Blocks");
    }
}
