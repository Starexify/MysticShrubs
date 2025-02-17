package net.nova.mysticshrubs.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.nova.mysticshrubs.data.loot.MSLootTableProvider;
import net.nova.mysticshrubs.data.tags.MSBiomeTagsProvider;

import java.util.concurrent.CompletableFuture;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.addProvider(new LangProvider(output));
        event.addProvider(new MSModelProvider(output));
        event.addProvider(new MSBiomeTagsProvider(output, lookupProvider));
        event.addProvider(new MSLootTableProvider(output, lookupProvider));
        event.addProvider(new DatapackProvider(output, lookupProvider));
        event.addProvider(new SoundsProvider(output));
    }
}
