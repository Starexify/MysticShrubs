package net.nova.mystic_shrubs.data;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.nova.mystic_shrubs.data.loot.MSLootTableProvider;
import net.nova.mystic_shrubs.data.tags.MSBiomeTagsProvider;
import net.nova.mystic_shrubs.data.worldgen.MSBiomeModifiers;
import net.nova.mystic_shrubs.data.worldgen.MSConfiguredFeatures;
import net.nova.mystic_shrubs.data.worldgen.MSPlacedFeatures;

import java.util.Set;

import static net.nova.mystic_shrubs.MysticShrubs.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(LangProvider::new);
        event.createProvider(MSModelProvider::new);
        event.createProvider(MSBiomeTagsProvider::new);
        event.createProvider(MSLootTableProvider::new);
        event.createProvider(SoundsProvider::new);
        event.createDatapackRegistryObjects(new RegistrySetBuilder()
                        .add(Registries.CONFIGURED_FEATURE, MSConfiguredFeatures::bootstrap)
                        .add(Registries.PLACED_FEATURE, MSPlacedFeatures::bootstrap)
                        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, MSBiomeModifiers::bootstrap),
                Set.of(MODID));
    }
}
