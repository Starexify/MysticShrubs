package net.nova.mysticshrubs;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
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

    // Add items to the vanilla creative tabs
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
            event.getEntries().putAfter(keyToPutAfter(event, Items.EMERALD), ModItems.EMERALD_PIECE.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(keyToPutAfter(event, ModItems.EMERALD_PIECE.get()), ModItems.EMERALD_SHARD.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey().equals(CreativeModeTabs.NATURAL_BLOCKS)) {
            event.getEntries().putAfter(keyToPutAfter(event, Items.NETHER_WART), ModItems.MYSTICAL_SEED.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private ItemStack keyToPutAfter(BuildCreativeModeTabContentsEvent event, Item item) {
        for (var iter = event.getEntries().iterator(); iter.hasNext(); ) {
            var stack = iter.next();
            if (stack.getKey().getItem() == item) {
                return stack.getKey();
            }
        }
        return null;
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
