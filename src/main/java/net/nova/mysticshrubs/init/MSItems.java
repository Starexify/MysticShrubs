package net.nova.mysticshrubs.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.mysticshrubs.item.EmeraldPiece;
import net.nova.mysticshrubs.item.EmeraldShard;
import net.nova.mysticshrubs.item.HeartDrop;

import static  net.nova.mysticshrubs.MysticShrubs.MODID;

public class MSItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static DeferredItem<Item> EMERALD_SHARD = ITEMS.register("emerald_shard", () -> new EmeraldShard(new Item.Properties()));
    public static DeferredItem<Item> EMERALD_PIECE = ITEMS.register("emerald_piece", () -> new EmeraldPiece(new Item.Properties()));
    public static DeferredItem<Item> HEART_DROP = ITEMS.register("heart_drop", () -> new HeartDrop(new Item.Properties().stacksTo(1)));
    public static DeferredItem<Item> MYSTICAL_SEED = ITEMS.register("mystical_seed", () -> new ItemNameBlockItem(MSBlocks.MYSTIC_SHRUB.get(), new Item.Properties()));
}
