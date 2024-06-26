package net.nova.mysticshrubs;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.nova.mysticshrubs.init.ModBlocks;
import net.nova.mysticshrubs.init.ModItems;
import net.nova.mysticshrubs.init.ModSounds;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(MysticShrubs.MODID)
public class MysticShrubs {
    public static final String MODID = "mystic_shrubs";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MysticShrubs(IEventBus bus) {

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModSounds.SOUND_EVENTS.register(bus);

        bus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        final ResourceKey<CreativeModeTab> tab = event.getTabKey();
        if (tab.equals(CreativeModeTabs.INGREDIENTS)) {
            event.getEntries().putAfter(Items.EMERALD.getDefaultInstance(), ModItems.EMERALD_PIECE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(ModItems.EMERALD_PIECE.get().getDefaultInstance(), ModItems.EMERALD_SHARD.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab.equals(CreativeModeTabs.NATURAL_BLOCKS)) {
            event.getEntries().putAfter(Items.NETHER_WART.getDefaultInstance(), ModItems.MYSTICAL_SEED.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
