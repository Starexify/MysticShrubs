package net.nova.mystic_shrubs.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.mystic_shrubs.item.EmeraldPiece;
import net.nova.mystic_shrubs.item.EmeraldShard;
import net.nova.mystic_shrubs.item.HeartDrop;

import static  net.nova.mystic_shrubs.MysticShrubs.MODID;

public class MSItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static DeferredItem<Item> EMERALD_SHARD = ITEMS.registerItem("emerald_shard", EmeraldShard::new);
    public static DeferredItem<Item> EMERALD_PIECE = ITEMS.registerItem("emerald_piece", EmeraldPiece::new);
    public static DeferredItem<Item> HEART_DROP = ITEMS.registerItem("heart_drop", properties -> new HeartDrop(properties.stacksTo(1)));
    public static DeferredItem<BlockItem> MYSTICAL_SEED = ITEMS.registerSimpleBlockItem("mystical_seed", MSBlocks.MYSTIC_SHRUB);
}
