package net.nova.mystic_shrubs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.player.PlayerPickItemEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.nova.mystic_shrubs.init.MSBlocks;
import net.nova.mystic_shrubs.init.MSItems;
import net.nova.mystic_shrubs.init.Sounds;
import net.nova.mystic_shrubs.worldgen.MSPlacedFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysticShrubs implements ModInitializer {
    public static final String MODID = "mystic_shrubs";
    public static final Logger LOGGER = LoggerFactory.getLogger(MysticShrubs.class);

    @Override
    public void onInitialize() {
        Sounds.initialize();
        MSBlocks.initialize();
        MSItems.initialize();

        // Pickup Sounds
        PlayerPickItemEvents.ENTITY.register((serverPlayer, entity, b) -> {
            Level level = serverPlayer.level();
            ItemStack item = entity instanceof ItemEntity itemEntity ? itemEntity.getItem() : null;

            if (item != null) {
                if (item.is(MSItems.EMERALD_SHARD) || item.is(MSItems.HEART_DROP)) {
                    SoundEvent sound = item.is(MSItems.EMERALD_SHARD) ? Sounds.EMERALD_SHARD_PICKUP : Sounds.COLLECT_HEART;
                    if (!level.isClientSide) {
                        level.playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), sound, SoundSource.PLAYERS, 0.7f, 1.0f);
                    }
                }
            }
            return null;
        });

        // Biome Modifiers
        BiomeModifications.addFeature(BiomeSelectors.tag(CAN_PLACE_MYSTIC_SHRUBS), GenerationStep.Decoration.VEGETAL_DECORATION, MSPlacedFeatures.MYSTIC_SHRUB_PLACED);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public static final TagKey<Biome> CAN_PLACE_MYSTIC_SHRUBS = TagKey.create(Registries.BIOME, MysticShrubs.rl("can_place_mystic_shrubs"));
}
