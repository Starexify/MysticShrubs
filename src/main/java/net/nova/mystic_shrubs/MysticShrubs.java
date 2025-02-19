package net.nova.mystic_shrubs;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.nova.mystic_shrubs.init.MSBlocks;
import net.nova.mystic_shrubs.init.MSItems;
import net.nova.mystic_shrubs.init.Sounds;

import static net.nova.mystic_shrubs.MysticShrubs.MODID;

@Mod(MODID)
public class MysticShrubs {
    public static final String MODID = "mystic_shrubs";

    public MysticShrubs(IEventBus bus) {
        Sounds.SOUND_EVENTS.register(bus);
        MSBlocks.BLOCKS.register(bus);
        MSItems.ITEMS.register(bus);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public static final TagKey<Biome> CAN_PLACE_MYSTIC_SHRUBS = TagKey.create(Registries.BIOME, MysticShrubs.rl("can_place_mystic_shrubs"));
}
