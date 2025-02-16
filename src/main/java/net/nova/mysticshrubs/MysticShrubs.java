package net.nova.mysticshrubs;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

public class MysticShrubs implements ModInitializer {
    public static final String MODID = "mystic_shrubs";

    @Override
    public void onInitialize() {

    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
