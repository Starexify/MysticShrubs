package net.nova.mysticshrubs;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.nova.mysticshrubs.init.MSBlocks;
import net.nova.mysticshrubs.init.MSItems;
import net.nova.mysticshrubs.init.Sounds;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

@Mod(MODID)
public class MysticShrubs {
    public static final String MODID = "mystic_shrubs";

    public MysticShrubs(IEventBus bus) {
        MSBlocks.BLOCKS.register(bus);
        MSItems.ITEMS.register(bus);
        Sounds.SOUND_EVENTS.register(bus);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
