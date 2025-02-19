package net.nova.mystic_shrubs.events;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.nova.mystic_shrubs.init.MSBlocks;
import net.nova.mystic_shrubs.init.MSItems;

import java.util.function.Supplier;

import static net.nova.mystic_shrubs.MysticShrubs.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MysticShrubsClient {
    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(MSBlocks.MYSTIC_SHRUB.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
        if (tabKey.equals(CreativeModeTabs.INGREDIENTS)) {
            putAfter(Items.EMERALD, MSItems.EMERALD_PIECE, event);
            putAfter(MSItems.EMERALD_PIECE.get(), MSItems.EMERALD_SHARD, event);
        }

        if (tabKey.equals(CreativeModeTabs.NATURAL_BLOCKS)) {
            putAfter(Items.NETHER_WART, MSItems.MYSTICAL_SEED, event);
        }
    }

    private static void putAfter(Item item, Supplier<? extends ItemLike> itemAfter, BuildCreativeModeTabContentsEvent event) {
        event.insertAfter(item.getDefaultInstance(), itemAfter.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
