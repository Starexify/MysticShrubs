package net.nova.mystic_shrubs.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.nova.mystic_shrubs.MysticShrubs;
import net.nova.mystic_shrubs.item.EmeraldPiece;
import net.nova.mystic_shrubs.item.EmeraldShard;
import net.nova.mystic_shrubs.item.HeartDrop;

import java.util.function.Function;

public class MSItems {
    public static Item EMERALD_SHARD = registerItem("emerald_shard", EmeraldShard::new);
    public static Item EMERALD_PIECE = registerItem("emerald_piece", EmeraldPiece::new);
    public static Item HEART_DROP = registerItem("heart_drop", properties -> new HeartDrop(properties.stacksTo(1)));
    public static Item MYSTICAL_SEED = registerItem("mystical_seed", createBlockItemWithUniqueName(MSBlocks.MYSTIC_SHRUB));

    // Methods
    public static <T extends Item> T registerItem(String name, Function<Item.Properties, T> factory) {
        return register(name, factory, new Item.Properties());
    }

    public static <T extends Item> T register(String name, Function<Item.Properties, T> factory, Item.Properties settings) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, MysticShrubs.rl(name));
        return Registry.register(BuiltInRegistries.ITEM, key, factory.apply(settings.setId(key)));
    }

    public static Function<Item.Properties, Item> createBlockItemWithUniqueName(Block block) {
        return settings -> new BlockItem(block, settings.useBlockDescriptionPrefix());
    }

    public static void initialize() {
        MysticShrubs.LOGGER.info("Registering Items");
    }
}
