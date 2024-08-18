package net.nova.mysticshrubs.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.nova.mysticshrubs.MysticShrubs;
import net.nova.mysticshrubs.data.loot_table.MSLootTableProvider;

import java.util.concurrent.CompletableFuture;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        try {
            DataGenerator generator = event.getGenerator();
            PackOutput output = generator.getPackOutput();
            CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

            generator.addProvider(true, new MSLootTableProvider(output, lookupProvider));
            generator.addProvider(true, new DatapackProvider(output, lookupProvider));

        } catch (RuntimeException e) {
            MysticShrubs.logger.error("Failed to gather data", e);
        }
    }
}
