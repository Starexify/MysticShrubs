package net.nova.mysticshrubs;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.nova.mysticshrubs.init.ModBlocks;
import net.nova.mysticshrubs.init.ModItems;
import net.nova.mysticshrubs.init.ModSounds;
import org.slf4j.Logger;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

import static net.nova.mysticshrubs.MysticShrubs.MODID;


@Mod(MODID)
public class MysticShrubs {
    public static final String MODID = "mystic_shrubs";
    public static final Logger logger = LoggerFactory.getLogger(MysticShrubs.class);

    public MysticShrubs(IEventBus bus) {
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModSounds.SOUND_EVENTS.register(bus);

        bus.addListener(this::addCreative);
    }

    // Add items to the vanilla creative tabs
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
        if (tabKey.equals(CreativeModeTabs.INGREDIENTS)) {
            putAfter(Items.EMERALD, ModItems.EMERALD_PIECE, event);
            putAfter(ModItems.EMERALD_PIECE.get(), ModItems.EMERALD_SHARD, event);
        }
        if (tabKey.equals(CreativeModeTabs.NATURAL_BLOCKS)) {
            putAfter(Items.NETHER_WART, ModItems.MYSTICAL_SEED, event);
        }
    }

    private static void putAfter(Item item, Supplier<? extends ItemLike> itemAfter, BuildCreativeModeTabContentsEvent event) {
        event.insertAfter(item.getDefaultInstance(), itemAfter.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
