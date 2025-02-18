package net.nova.mysticshrubs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.nova.mysticshrubs.init.MSBlocks;
import net.nova.mysticshrubs.init.MSItems;

public class MSClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // RenderTypes
        BlockRenderLayerMap.INSTANCE.putBlock(MSBlocks.MYSTIC_SHRUB, RenderType.cutout());

        // Creative Tab Placement
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.EMERALD, MSItems.EMERALD_PIECE);
            entries.addAfter(MSItems.EMERALD_PIECE, MSItems.EMERALD_SHARD);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            entries.addAfter(Items.NETHER_WART, MSItems.MYSTICAL_SEED);
        });
    }
}